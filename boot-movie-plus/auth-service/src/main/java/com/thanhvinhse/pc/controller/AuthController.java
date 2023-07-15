package com.thanhvinhse.pc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhvinhse.pc.domain.common.ApiResponseEntity;
import com.thanhvinhse.pc.domain.payload.dto.request.LoginRequest;
import com.thanhvinhse.pc.domain.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	private final ObjectMapper objectMapper;
	
	@ResponseBody 
	@RequestMapping("login")
	public String login(@RequestBody LoginRequest loginRequest) throws JsonProcessingException {
		ApiResponseEntity response = new ApiResponseEntity();
		
		try {
			response = authService.loginExecute(loginRequest, response);
		} catch (Exception e) {
			response.setData(null);
			response.setErrors(List.of(e.getMessage()));
			response.setStatus(0);
		}
		
		return objectMapper.writeValueAsString(response);
	}
}
