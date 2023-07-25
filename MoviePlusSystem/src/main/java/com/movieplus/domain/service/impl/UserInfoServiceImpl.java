package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.db.read.RUserInfoMapper;
import com.movieplus.domain.entity.UserInfo;
import com.movieplus.domain.service.UserInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
	
	private final String SERVICE_NAME = "UserInfoService";
	private final RUserInfoMapper rUserInfoMapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public UserInfo getUserByUserName(String userName) {
		try {			
			String whereCondition = String.format("username = '%s'", userName);
			List<Map<String, Object>> listUserInfo = rUserInfoMapper.selectWhere(whereCondition);
			if(!listUserInfo.isEmpty()) {				
				return objectMapper.convertValue(listUserInfo, UserInfo.class);
			}
		} catch (Exception e) {
			log.error("{} ERROR getUserByUserName: {}", SERVICE_NAME, e);
		}
		
		return null;
	}

}
