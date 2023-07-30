package com.movieplus.domain.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movieplus.controller.EntryUserInfoController.RegistRequest;
import com.movieplus.domain.entity.UserInfo;
import com.movieplus.domain.service.RegistService;
import com.movieplus.domain.service.UserInfoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistServiceImpl implements RegistService {
	
	private final UserInfoService userInfoService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public String doRegist(RegistRequest request) {
		
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(request, userInfo);
		userInfo.setPassword(passwordEncoder.encode(request.getPassword()));
		userInfo.setEmailValidFlag((byte) 0);
		userInfo.setUpdateTime(LocalDate.now());
		userInfo.setRegistTime(LocalDate.now());

		List<String> userIds = userInfoService.save(List.of(userInfo));
		
		return userIds.get(0);
	}

}
