package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.UserService;
import com.example.demo.entity.Result;
import com.example.demo.entity.User;

@RestController
@RequestMapping(value = "/")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
     * 注册
     * @return Result
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result regist(@RequestBody JSONObject data){
    	String username = (String) data.get("username");
    	String password = (String) data.get("password");
    	User user = new User(0, username, password, null, null);
        return userService.register(user);
    }

    /**
     * 登录
     * @return Result
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody JSONObject data, HttpServletResponse httpServletResponse){
    	String username = (String) data.get("username");
    	String password = (String) data.get("password");
        return userService.login(username, password, httpServletResponse);
    }
    
    /**
     * 更改密码
     * @param data
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public Result changePassword(@RequestBody JSONObject data){
    	String username = (String) data.get("username");
    	String password = (String) data.get("password");
        return userService.updatePassword(username, password);
    }
    
    /**
     * 更改云盘账号
     * @param data
     * @return
     */
    @RequestMapping(value = "/updateDiskAccount", method = RequestMethod.POST)
    public Result changeDiskAccount(@RequestBody JSONObject data, HttpServletRequest httpServletRequest){
    	String diskName = (String) data.get("diskName");
    	String diskPassword = (String) data.get("password");
        return userService.updateDiskAccount(diskName, diskPassword, httpServletRequest);
    }
}
