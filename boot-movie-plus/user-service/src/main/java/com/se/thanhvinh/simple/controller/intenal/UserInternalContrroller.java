package com.se.thanhvinh.simple.controller.intenal;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.thanhvinh.simple.domain.common.GeneratorCommonUtil;
import com.se.thanhvinh.simple.domain.common.ObjectMapperCommonUtil;
import com.se.thanhvinh.simple.domain.entity.User;
import com.se.thanhvinh.simple.domain.payload.request.GetInternalApiRequest;
import com.se.thanhvinh.simple.domain.payload.request.InsertIntenalApiRequest;
import com.se.thanhvinh.simple.domain.service.UserInternalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/internal")
public class UserInternalContrroller {

	private final UserInternalService userInternalService;
	
	@PostMapping("/getUser")
	public @ResponseBody String getUser(@Valid @RequestBody GetInternalApiRequest request) {
		try {
			Object data = userInternalService.doGetUser(request);
			
			return GeneratorCommonUtil.getResponseBodySuccess(data);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@PostMapping("/insertUser")
	public @ResponseBody String insertUser(@Valid @RequestBody InsertIntenalApiRequest request) {
		try {
			List<User> userRequest = ObjectMapperCommonUtil.convertValue(request.getRecord(), new TypeReference<List<User>>() {});
			List<Integer> userIds = userInternalService.doInsertUser(userRequest);
			return GeneratorCommonUtil.getResponseBodySuccess(userIds);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
