package com.se.thanhvinh.simple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public AuthTokenFilter authTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new UserAuthenticationSuccessHandler();
	}
	
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new UserAuthenticationFailureHandler();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeHttpRequests().requestMatchers("/login", "/regist").permitAll();
		
		http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
