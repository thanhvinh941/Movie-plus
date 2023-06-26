package com.se.thanhvinh.simple.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.se.thanhvinh.simple.domain.common.ObjectMapperCommonUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("AuthenticationFailureHandler - Authentication: {}", ObjectMapperCommonUtil.writeValueAsString(exception));
		// TODO Auto-generated method stub
		// TODO store User Login in to login_failed_log
	}

}
