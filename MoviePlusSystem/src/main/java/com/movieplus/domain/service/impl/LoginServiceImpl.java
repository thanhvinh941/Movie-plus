package com.movieplus.domain.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.movieplus.controller.LoginController.LoginRequest;
import com.movieplus.domain.entity.UserInfo;
import com.movieplus.domain.service.LoginService;
import com.movieplus.domain.service.UserInfoService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	@Getter
	@Setter
	private class LoginResponse{
		private String token;
		private Long expirationTime;
	}
	
	private final UserInfoService userInfoService;
	
	@Override
	public String doLogin(LoginRequest loginRequest) throws Exception {
		
		UserInfo userInfo = userInfoService.getUserByUserName(loginRequest.getUserName());
		if(Objects.isNull(userInfo)) {
			throw new Exception("User not found");
		}
		
		
		
		return null;
	}

}
