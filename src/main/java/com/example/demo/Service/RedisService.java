package com.example.demo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Result;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
	private static Logger logger = LoggerFactory.getLogger(RedisService.class);
	
	@Autowired
	private JedisPool jedisPool;
	
	public Jedis getResource() {
		return jedisPool.getResource();
	}
	
	@SuppressWarnings("deprecation")
	public void returnResource(Jedis jedis) {
		if(jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}
	
	public Result set(String key, String value) {
		Jedis jedis = null;
		Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
		try {
			jedis = getResource();
			jedis.set(key, value);
			result.setMsg("插入成功");
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Redis set error:message:" + e.getMessage() + " key:" + key + " value:" + value);
			result.setMsg(e.getMessage());
		}
		finally {
			returnResource(jedis);		
		}
		return result;
	}
	
	public String get(String key) {
		String res = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			res = jedis.get(key);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Redis get error:key" + key);
		}
		finally {
			returnResource(jedis);
		}
		return res;
	}
	
	public boolean hasKey(String key) {
		boolean res = false;
		Jedis jedis = null;
		try {
			jedis = getResource();
			res = jedis.exists(key);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Redis check key error:key" + key);
		}
		return res;
	}
}
