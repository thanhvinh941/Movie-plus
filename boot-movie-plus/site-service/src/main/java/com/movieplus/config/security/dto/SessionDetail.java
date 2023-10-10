package com.movieplus.config.security.dto;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionDetail {

	private final RedisTemplate<String, Object> redisTemplate;
	private final ObjectMapper objectMapper;
	
	public void setSessionByKey(String key, Object data) {
		redisTemplate.opsForValue().set(key, data);
	}
	
	public String getSessionByKey(String key) throws JsonProcessingException {
		Object result = redisTemplate.opsForValue().get(key);
		return objectMapper.writeValueAsString(result);
	}
}
