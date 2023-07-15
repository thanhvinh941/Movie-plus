package com.thanhvinhse.pc.domain.common;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.thanhvinhse.pc.domain.entity.UserInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommon {

	private final CallInternalApi callInternalApi;
	
	public List<UserInfo> getUserInfo(GetInternalApiRequest request) throws JsonProcessingException{
		try {
			return callInternalApi.callPostMenthodForObject(request, 
					CallInternalApi.USER_SERVICE, 
					UrlConstant.USER_GET_USER_INFO, 
					new TypeReference<List<UserInfo>>() {});
		} catch (Exception e) {
			// TODO: handle exception
		}
		return List.of();
	}
}
