package com.se.thanhvinh.simple.config;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationHelper {

	@Value("${app.auth.tokenSecret}")
	private String tokenSecret;
	
	@Value("${app.auth.tokenExpirationMsec}")
	private Integer tokenExpirationMsec;
	
	@Value("${app.cookie.name}")
	private String cookieName;
	
	public void addAuthentication(HttpServletResponse res, Authentication auth) {

		String authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		String jwt = Jwts.builder()
				.setSubject(auth.getName())
				.claim("authorities", authorities)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + tokenExpirationMsec))
				.signWith(SignatureAlgorithm.HS512, tokenSecret)
				.compact();
		
		Cookie cookie = new Cookie(cookieName, jwt);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		res.addCookie(cookie);
	}

	public Authentication getAuthentication(HttpServletRequest request) {

		Cookie cookie = WebUtils.getCookie(request, cookieName);
		String token = cookie != null ? cookie.getValue() : null;

		if (token != null) {
			Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();

			String userName = claims.getSubject();
			//TODO get userInfo
			return userName != null ? new UsernamePasswordAuthenticationToken(userName, null, null) : null;
		}
		return null;
	}

}
