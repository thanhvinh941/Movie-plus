package com.movieplus.domain.service.impl;

import org.springframework.stereotype.Service;

import com.movieplus.domain.entity.UserToken;
import com.movieplus.domain.repository.UserTokenRepository;
import com.movieplus.domain.service.UserTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserTokenServiceImpl implements UserTokenService {

	private final UserTokenRepository repository;
	
	@Override
	public String save(UserToken userToken) {
		try {			
			UserToken token = repository.save(userToken);
			return token.getRefeshToken();
		} catch (Exception e) {
			return null;
		}
	}
}
