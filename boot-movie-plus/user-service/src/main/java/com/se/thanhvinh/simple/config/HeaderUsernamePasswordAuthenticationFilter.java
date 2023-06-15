package com.se.thanhvinh.simple.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HeaderUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public HeaderUsernamePasswordAuthenticationFilter() {
		super();
		this.setFilterProcessesUrl("/external/**");
		this.setPostOnly(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * UsernamePasswordAuthenticationFilter#obtainPassword(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		return request.getHeader(this.getPasswordParameter());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * UsernamePasswordAuthenticationFilter#obtainUsername(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		return request.getHeader(this.getPasswordParameter());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return super.attemptAuthentication(request, response);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String token = servletRequest.getHeader(SPRING_SECURITY_FORM_USERNAME_KEY);
		
		
		// TODO Auto-generated method stub
		super.doFilter(request, response, chain);
	}
}
