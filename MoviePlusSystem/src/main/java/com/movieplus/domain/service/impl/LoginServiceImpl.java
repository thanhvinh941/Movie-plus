package com.movieplus.domain.service.impl;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.JwtUtil;
import com.movieplus.config.UserSession;
import com.movieplus.domain.entity.UserInfo;
import com.movieplus.domain.entity.UserToken;
import com.movieplus.domain.payload.request.LoginRequest;
import com.movieplus.domain.service.LoginService;
import com.movieplus.domain.service.UserInfoService;
import com.movieplus.domain.service.UserTokenService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	@Getter
	@Setter
	private class LoginResponse{
		private String accessToken;
		private int expirationTime;
		private String refreshToken;
	}
	
	private final UserInfoService userInfoService;
	private final UserTokenService userTokenService;
	private final PasswordEncoder passwordEncoder;
	private final RedisTemplate<String, Object> redisTemplate;
	private final ObjectMapper objectMapper;
	
	private final JwtUtil jwtUtil;
	
	@Value("${jwt.expriration}")
	private int expriration;
	
	@Override
	public LoginResponse doLogin(LoginRequest loginRequest) throws Exception {
		
		UserInfo userInfo = userInfoService.getUserByUserName(loginRequest.getUsername());
		if(Objects.isNull(userInfo)) {
			throw new Exception("User not found");
		}
		
		if(userInfo.getEmailValidFlag() == 0) {
			throw new Exception("User not found");
		}
		
		String userPassword = userInfo.getPassword();
		if(passwordEncoder.matches(loginRequest.getPassword(), userPassword)) {
			String accessToken = jwtUtil.createToken();
			String refreshToken = UUID.randomUUID().toString();
			
			UserToken userToken = new UserToken();
			userToken.setAccessToken(accessToken);
			userToken.setRefeshToken(refreshToken);
			userToken.setExpirationTime(expriration * 7);
			
			if(!Objects.isNull(userTokenService.save(userToken))){
				
			}
			UserSession userSession = new UserSession();
			BeanUtils.copyProperties(userInfo, userSession);
			redisTemplate.opsForValue().set(accessToken, objectMapper.writeValueAsString(userSession), (new Date()).getTime() + expriration);
			
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setExpirationTime(expriration);
			loginResponse.setAccessToken(accessToken);
			loginResponse.setRefreshToken(refreshToken);
			return loginResponse;
		}else {
			throw new Exception("User not found");
		}
		
	}

}
