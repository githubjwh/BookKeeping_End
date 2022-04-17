//package com.example.demo.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSONObject;
//import com.example.demo.Service.RedisService;
//import com.example.demo.entity.Result;
//
//@RestController
//public class RedisController {
//	@Autowired
//	private RedisService redisService;
//	
//	@RequestMapping(value = "/redis/set", method = RequestMethod.POST)
//	public Result redisSet(@RequestBody JSONObject data) {
//		String key = (String) data.get("key");
//		String value = (String) data.get("value");
//		return redisService.set(key, value);
//	}
//	
//	@RequestMapping(value = "/redis/set", method = RequestMethod.POST)
//	public String redisGet(@RequestBody JSONObject data) {
//		String key = (String) data.get("key");
//		return redisService.get(key);
//	}
//}
