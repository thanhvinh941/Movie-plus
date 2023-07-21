package com.movieplus.config;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUtil {
	public boolean validateJwtToken(String jwt) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getUserNameFromJwtToken(String jwt) {
		// TODO Auto-generated method stub
		return null;
	}

}
