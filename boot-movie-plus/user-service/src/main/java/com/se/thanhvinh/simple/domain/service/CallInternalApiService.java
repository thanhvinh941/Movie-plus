package com.se.thanhvinh.simple.domain.service;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

public interface CallInternalApiService {

	public <T> T callInternalApiWithParam(Map<String, Object> requestParam, TypeReference<T> typeReference);
	
	public <T> T callInternalApiWithObject(Object requestObject, TypeReference<T> typeReference);
}
