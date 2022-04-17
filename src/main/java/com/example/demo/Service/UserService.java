package com.example.demo.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.entity.Result;
import com.example.demo.entity.User;

@Service
@Transactional(rollbackFor = RuntimeException.class)

public class UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisService redisService;
	
	/**
     * 登录
	 * @param httpServletResponse 
     * @param username用户名， password密码
     * @return Result
     */
	 public Result login(String username, String password, HttpServletResponse httpServletResponse) {
	        Result result = new Result();
	        result.setSuccess(false);
	        result.setDetail(null);
	        try {
	            Integer userId= userMapper.login(username, password);
	            if(userId == null){
	                result.setMsg("用户名或密码错误");
	            }else{
	            	String tokenString = TokenService.createToken(username);
	            	System.out.println("token" + tokenString);
	            	redisService.set(tokenString, userId.toString());
	            	Cookie cookie = new Cookie("token", tokenString);
	            	cookie.setPath("/");
	                httpServletResponse.addCookie(cookie); 
	                result.setMsg("登录成功");
	                result.setSuccess(true);
	                JSONObject token_json = new JSONObject();
	                token_json.put("token", tokenString);
	                result.setDetail(token_json.toJSONString());
	            }
	        } catch (Exception e) {
	            result.setMsg(e.getMessage());
	            e.printStackTrace();
	        }
	        return result;
	    }
	 
	 /**
	  * 注册 user
	  * @param user
	  * @return Result
	  */
	 public Result register(User user) {
	        Result result = new Result();
	        result.setSuccess(false);
	        result.setDetail(null);
	        try {
	            User u = userMapper.getUserByUsername(user.getUsername());
	            if(u != null){
	                result.setMsg("用户名已存在");

	            }else{
	                userMapper.regist(user);;
	                result.setMsg("注册成功");
	                result.setSuccess(true);
	            }
	        } catch (Exception e) {
	            result.setMsg(e.getMessage());
	            e.printStackTrace();
	        }
	        return result;
	        }
	 
	 /**
	  * 更改密码
	  * @param username
	  * @param password
	  * @return
	  */
	 public Result updatePassword(String username, String password) {
		 Result result = new Result();
	        result.setSuccess(false);
	        result.setDetail(null);
	        try {
	        	User user = userMapper.getUserByUsername(username);
	            Integer userId= user.getId();
	            userMapper.updatePassword(password, userId);
	            result.setMsg("密码更改成功");
	            result.setSuccess(true);
	        } catch (Exception e) {
	            result.setMsg(e.getMessage());
	            e.printStackTrace();
	        }
	        return result;
	 }
	 
	 /**
	  * 更改云盘账号
	  * @param diskName
	  * @param diskPassword
	 * @param httpServletRequest 
	  * @return
	  */
	 public Result updateDiskAccount(String diskName, String diskPassword, HttpServletRequest httpServletRequest) {
		 Result result = new Result();
	        result.setSuccess(false);
	        result.setDetail(null);
	        try {
	        	String tokenString = null;
	        	Cookie[] cookies = httpServletRequest.getCookies();
	        	if(cookies != null) {
	        		for(Cookie cookie : cookies) {
	        			if(cookie.getName().equals("token")) {
	        				tokenString = cookie.getValue();
	        				break;
	        			}
	        		}
	        	}
	        	if(tokenString != null) {
	        		Integer userId = Integer.valueOf(redisService.get(tokenString));
	        		userMapper.updateDiskName(diskName, userId);
	        		userMapper.updateDiskPassword(diskPassword, userId);
	        		result.setMsg("云盘更改成功");
		            result.setSuccess(true);
	        	}
	        	else {
	        		result.setMsg("云盘更改失败");
		            result.setSuccess(false);
	        	}
	        } catch (Exception e) {
	            result.setMsg(e.getMessage());
	            e.printStackTrace();
	        }
	        return result;
	 }
}
