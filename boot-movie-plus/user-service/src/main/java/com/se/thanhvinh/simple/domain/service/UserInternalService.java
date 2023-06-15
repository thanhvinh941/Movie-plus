package com.se.thanhvinh.simple.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.se.thanhvinh.simple.domain.entity.User;
import com.se.thanhvinh.simple.domain.payload.request.GetInternalApiRequest;
import com.se.thanhvinh.simple.domain.payload.request.UpdateInternalApiRequest;

import jakarta.validation.Valid;

@Service
public interface UserInternalService {

	Object doGetUser(GetInternalApiRequest request);

	List<Integer> doInsertUser(List<User> userRequest);

	Integer doUpdateUser(@Valid UpdateInternalApiRequest request);

}
