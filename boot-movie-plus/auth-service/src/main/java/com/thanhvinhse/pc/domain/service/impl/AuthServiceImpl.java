package com.thanhvinhse.pc.domain.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thanhvinhse.pc.domain.common.ApiResponseEntity;
import com.thanhvinhse.pc.domain.common.GetInternalApiRequest;
import com.thanhvinhse.pc.domain.common.UserCommon;
import com.thanhvinhse.pc.domain.entity.UserInfo;
import com.thanhvinhse.pc.domain.payload.dto.request.LoginRequest;
import com.thanhvinhse.pc.domain.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private PasswordEncoder passwordEncoder;
	
	private final UserCommon userCommon;
	
	@Override
	public ApiResponseEntity loginExecute(LoginRequest loginRequest, ApiResponseEntity response) {

		try {
			
			loginValidationRequest(loginRequest);
			
			loginMainProcess(loginRequest, response);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return null;
	}

	private void loginMainProcess(LoginRequest loginRequest, ApiResponseEntity response) {
		// TODO Auto-generated method stub
		
	}

	private void loginValidationRequest(LoginRequest loginRequest) throws Exception {
		String userName = loginRequest.getUserName();
		
		UserInfo userInfo = getUserInfo(userName);
		if(Objects.isNull(userInfo)) {
			throw new Exception("user dosen't exist ");
		}
		
		if(passwordEncoder.matches(loginRequest.getPassword(), userInfo.getPassword())) {
			
		}else {
			
		}
	}

	private UserInfo getUserInfo(String userName) throws JsonProcessingException {
		
		GetInternalApiRequest request = new GetInternalApiRequest();
		request.setConditionStr(String.format("user_name = '%s'", userName));
		
		List<UserInfo> userInfos = userCommon.getUserInfo(request);
		
		return !userInfos.isEmpty() ? userInfos.get(0) : null;
	}

}
