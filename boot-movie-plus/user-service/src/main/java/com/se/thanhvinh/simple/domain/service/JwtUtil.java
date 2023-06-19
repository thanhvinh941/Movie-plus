package com.se.thanhvinh.simple.domain.service;

public interface JwtUtil {

	boolean validateJwtToken(String jwt);

	String getUserNameFromJwtToken(String jwt);

}
