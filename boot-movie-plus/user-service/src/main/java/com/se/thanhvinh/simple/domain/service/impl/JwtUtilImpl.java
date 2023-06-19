package com.se.thanhvinh.simple.domain.service.impl;

import org.springframework.stereotype.Service;

import com.se.thanhvinh.simple.domain.service.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUtilImpl implements JwtUtil {
	@Override
	public boolean validateJwtToken(String jwt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUserNameFromJwtToken(String jwt) {
		// TODO Auto-generated method stub
		return null;
	}

}
