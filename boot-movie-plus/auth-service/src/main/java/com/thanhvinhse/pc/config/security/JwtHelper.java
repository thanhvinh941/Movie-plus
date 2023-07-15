package com.thanhvinhse.pc.config.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.thanhvinhse.pc.config.AppProperties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtHelper {

	private final AppProperties appProperties;
	
	public String createToken(String userId) {
		Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
	}
}
