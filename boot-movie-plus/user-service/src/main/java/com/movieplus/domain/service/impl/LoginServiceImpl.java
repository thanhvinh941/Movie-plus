package com.movieplus.domain.service.impl;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	@Getter
	@Setter
	public static class LoginResponse<T extends UserSession>{
		
		private T user;
		private Token token;
		
		@Getter
		@Setter
		public static class Token{			
			private String accessToken;
			private long accessTokenExpiry;
			private String refreshToken;
		}
	}
	
	private final UserInfoService userInfoService;
	private final UserTokenService userTokenService;
	private final PasswordEncoder passwordEncoder;
	private final RedisTemplate<String, Object> redisTemplate;
	private final ObjectMapper objectMapper;
	
	private final JwtUtil jwtUtil;
	
	@Value("${jwt.expriration}")
	private int expriration;
	
	@SuppressWarnings("removal")
	@Override
	public LoginResponse doLogin(LoginRequest loginRequest) throws Exception {
		
		UserInfo userInfo = userInfoService.getUserByUserName(loginRequest.getUsername());
		if(Objects.isNull(userInfo)) {
			throw new Exception("User not found");
		}
		
		if(userInfo.getEmailValidFlag() == 0) {
			throw new Exception("User not found");
		}
		
		if((loginRequest.isAdmin() ? new Byte("1") : new Byte("0")).equals(userInfo.getIsAdmin())) {
			throw new Exception("User no permission");
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
			
			LoginResponse loginResponse = new LoginResponse();

			UserSession userSession = new UserSession();
			BeanUtils.copyProperties(userInfo, userSession);
			
			new Thread(() -> {		
				try {
					String userSessionStr = objectMapper.writeValueAsString(userSession);
					redisTemplate.opsForValue().set(accessToken, userSessionStr);
				} catch (JsonProcessingException e) {
					log.error("SET Redis ERROR by key: {}", accessToken, e);
				}
			}).start();
			
			LoginResponse.Token token = new LoginResponse.Token();
			token.setAccessTokenExpiry(expriration + (new Date()).getTime());
			token.setAccessToken(accessToken);
			token.setRefreshToken(refreshToken);
			
			loginResponse.setUser(userSession);
			loginResponse.setToken(token);
			
			return loginResponse;
		}else {
			throw new Exception("User not found");
		}
		
	}

}
