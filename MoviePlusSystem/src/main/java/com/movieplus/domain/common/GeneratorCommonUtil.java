package com.movieplus.domain.common;

import java.util.List;

import com.movieplus.domain.payload.response.ApiResponse;

public class GeneratorCommonUtil {

	public static String getResponseBodySuccess(Object data) {
		ApiResponse response = new ApiResponse();
		response.setData(data);
		response.setStatus(1);
		response.setErrors(List.of());
		
		return ObjectMapperCommonUtil.writeValueAsString(response);
	}
	
	public static String getResponseBodyError(List<String> errors) {
		ApiResponse response = new ApiResponse();
		response.setData(null);
		response.setStatus(0);
		response.setErrors(errors);
		
		return ObjectMapperCommonUtil.writeValueAsString(response);
	}
	
}
