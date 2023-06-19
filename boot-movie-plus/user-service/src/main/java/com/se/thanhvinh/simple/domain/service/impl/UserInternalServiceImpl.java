package com.se.thanhvinh.simple.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.se.thanhvinh.simple.domain.entity.User;
import com.se.thanhvinh.simple.domain.payload.request.GetInternalApiRequest;
import com.se.thanhvinh.simple.domain.payload.request.UpdateInternalApiRequest;
import com.se.thanhvinh.simple.domain.service.UserInternalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserInternalServiceImpl implements UserInternalService {
	@Override
	public Object doGetUser(GetInternalApiRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> doInsertUser(List<User> userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer doUpdateUser(@Valid UpdateInternalApiRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
