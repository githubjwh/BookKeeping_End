package com.example.demo.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
@PropertySource("classpath:application.properties")
public class RedisConfig {
	private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	
	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;
	
	@Value("${spring.redis.jedis.pool.min-idle}")
	private int minIdle;

	@Value("${spring.redis.jedis.pool.max-wait}")
	private long maxWaitMillis;
	
	public JedisPoolConfig getRedisConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisPoolConfig.setMinIdle(minIdle);
		return jedisPoolConfig;
	}
	
	@Bean
	public JedisPool getJedisPool() {
		JedisPoolConfig jedisPoolConfig = getRedisConfig();
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
		logger.info("init jedispool");
		return jedisPool;
	}

	public String getRedisHost() {
		return host;
	}
	
	public int getRedisPort() {
		return port;
	}
	
	public int getRedisTimeout() {
		return timeout;
	}
	
	public int getRedisMaxIdle() {
		return maxIdle;
	}
	
	public long getRedisMaxWait() {
		return maxWaitMillis;
	}
}
