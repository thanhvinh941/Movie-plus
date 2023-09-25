package com.movieplus.config;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberAuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private JwtUtil jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(header) || header.isEmpty() || !header.startsWith("Bearer ")) {
        	filterChain.doFilter(request, response);
            return;
        }
        
        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!jwtUtils.validateJwtToken(token)) {
        	filterChain.doFilter(request, response);
            return;
        }
        
        String userSessionStr = (String) redisTemplate.opsForValue().get(token);
        MemberSession userDetails = objectMapper.readValue(userSessionStr, MemberSession.class);
        UsernamePasswordAuthenticationToken
	        authentication = new UsernamePasswordAuthenticationToken(
	            userDetails, null,
	            List.of()
	        );
	
	    authentication.setDetails(
	        new WebAuthenticationDetailsSource().buildDetails(request)
	    );
	
	    SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

}
