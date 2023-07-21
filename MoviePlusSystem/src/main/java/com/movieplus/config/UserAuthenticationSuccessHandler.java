package com.movieplus.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("AuthenticationSuccessHandler - Authentication: {}", ObjectMapperCommonUtil.writeValueAsString(authentication));
		UserCredentials userCredentials = (UserCredentials) authentication.getCredentials();
		redisTemplate.opsForValue().set(userCredentials.getAccessToken(), userCredentials);;
	}

}
