package com.se.thanhvinh.simple.domain.common;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.thanhvinh.simple.domain.payload.response.InternalApiResponse;

public class GeneratorCommonUtil {

	public static String getResponseBodySuccess(Object data) {
		InternalApiResponse response = new InternalApiResponse();
		response.setData(data);
		response.setStatus(1);
		response.setErrors(List.of());
		
		return writeValueAsString(response);
	}
	
	public static String getResponseBodyError(List<String> errors) {
		InternalApiResponse response = new InternalApiResponse();
		response.setData(null);
		response.setStatus(1);
		response.setErrors(errors);
		
		return writeValueAsString(response);
	}
	
	public static String writeValueAsString(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			return "";
		}
	}
}
