package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	                result.setMsg("登录成功");
	                result.setSuccess(true);
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
}
