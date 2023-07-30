package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.db.read.RUserInfoMapper;
import com.movieplus.domain.entity.UserInfo;
import com.movieplus.domain.repository.UserInfoRepository;
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
	private final UserInfoRepository userInfoRepository;
	
	@Override
	public UserInfo getUserByUserName(String userName) {
		try {			
			String whereCondition = String.format("username = '%s'", userName);
			List<Map<String, Object>> listUserInfo = rUserInfoMapper.selectWhere(whereCondition);
			if(!listUserInfo.isEmpty()) {				
				return objectMapper.convertValue(listUserInfo.get(0), UserInfo.class);
			}
		} catch (Exception e) {
			log.error("{} ERROR getUserByUserName: {}", SERVICE_NAME, e);
		}
		
		return null;
	}

	@Override
	public List<String> save(List<UserInfo> records) {
		try {			
			List<UserInfo> userInfos = userInfoRepository.saveAll(records);
			return userInfos.stream()
					.map(UserInfo::getId)
					.collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

}
