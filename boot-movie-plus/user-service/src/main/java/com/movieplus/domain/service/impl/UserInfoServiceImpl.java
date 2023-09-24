package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperUtil;
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
	private final RUserInfoMapper mapper;
	private final ObjectMapper objectMapper;
	private final UserInfoRepository repository;
	
	@Override
	public UserInfo getUserByUserName(String userName) {
		try {			
			String whereCondition = String.format("username = '%s'", userName);
			List<Map<String, Object>> listUserInfo = mapper.selectWhere(whereCondition, 0, 0, List.of());
			if(!listUserInfo.isEmpty()) {				
				return objectMapper.convertValue(listUserInfo.get(0), UserInfo.class);
			}
		} catch (Exception e) {
			log.error("{} ERROR getUserByUserName: {}", SERVICE_NAME, e);
		}
		
		return null;
	}

	@Override
	public List<String> save(List<UserInfo> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperUtil.writeValueAsString(records));
			List<UserInfo> userInfos = repository.saveAll(records);
			return userInfos.stream()
					.map(UserInfo::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

}
