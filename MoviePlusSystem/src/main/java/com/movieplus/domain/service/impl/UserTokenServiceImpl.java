package com.movieplus.domain.service.impl;

import org.springframework.stereotype.Service;

import com.movieplus.domain.common.ObjectMapperCommonUtil;
import com.movieplus.domain.entity.UserToken;
import com.movieplus.domain.repository.UserTokenRepository;
import com.movieplus.domain.service.UserTokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTokenServiceImpl implements UserTokenService {

	private final UserTokenRepository repository;
	
	@Override
	public String save(UserToken userToken) {
		try {
			log.info("Do save with request: {}", ObjectMapperCommonUtil.writeValueAsString(userToken));
			UserToken token = repository.save(userToken);
			return token.getRefeshToken();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			return null;
		}
	}
}
