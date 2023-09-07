package com.movieplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MultiSecurityConfig {

	@Bean
	public UserAuthTokenFilter authTokenFilter() {
		return new UserAuthTokenFilter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Order(1)
	public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.securityMatcher("/api/user/**")
			.authorizeHttpRequests(authorize -> authorize
					.anyRequest().authenticated()
				);

		http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.securityMatcher("/api/admin/**")
			.authorizeHttpRequests(authorize -> authorize
					.anyRequest().hasRole("ADMIN")
				);

		http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
