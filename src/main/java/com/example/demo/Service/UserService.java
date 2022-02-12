package com.example.demo.Service;

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
	
	/**
     * 登录
     * @param username用户名， password密码
     * @return Result
     */
	 public Result login(String username, String password) {
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
	  * @param user
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
	 
	 
}
