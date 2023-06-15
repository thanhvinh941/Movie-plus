package com.se.thanhvinh.simple.config;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;

public class HeaderUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	  public HeaderUsernamePasswordAuthenticationFilter() {
	    super();
	    this.setFilterProcessesUrl("/**");
	    this.setPostOnly(false);
	  }

	  /* (non-Javadoc)
	   * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainPassword(javax.servlet.http.HttpServletRequest)
	   */
	  @Override
	  protected String obtainPassword(HttpServletRequest request) {
	    return request.getHeader(this.getPasswordParameter());
	  }

	  /* (non-Javadoc)
	   * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainUsername(javax.servlet.http.HttpServletRequest)
	   */
	  @Override
	  protected String obtainUsername(HttpServletRequest request) {
	    return request.getHeader(this.getPasswordParameter());
	  }

	}
