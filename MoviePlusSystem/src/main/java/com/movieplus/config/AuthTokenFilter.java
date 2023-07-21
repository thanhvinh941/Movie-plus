package com.movieplus.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

	public static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";
	
	@Autowired
	private AuthenticationHelper authenticationHelper;
	
	@Autowired
	private JwtUtil jwtUtils;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Authentication authentication = authenticationHelper.getAuthentication(request);
		
//		// Get authorization header and validate
//        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (header.isEmpty() || !header.startsWith("Bearer ")) {
//        	filterChain.doFilter(request, response);
//            return;
//        }
//        
//     // Get jwt token and validate
//        final String token = header.split(" ")[1].trim();
//        if (!jwtUtils.validateJwtToken(token)) {
//        	filterChain.doFilter(request, response);
//            return;
//        }
//        
//        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(token));
//        UsernamePasswordAuthenticationToken
//	        authentication = new UsernamePasswordAuthenticationToken(
//	            userDetails, null,
//	            userDetails == null ?
//	                List.of() : userDetails.getAuthorities()
//	        );
//	
//	    authentication.setDetails(
//	        new WebAuthenticationDetailsSource().buildDetails(request)
//	    );
	
	    SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

}
