package com.se.thanhvinh.simple.domain.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperCommonUtil {
	
	public static <T> T convertValue(Object data, TypeReference<T> reference) {
		try {
			return initObjectMapper().convertValue(data, reference);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String writeValueAsString(Object object) {
		try {
			return initObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			return "";
		}
	}
	
	private static ObjectMapper initObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		
		return mapper;
	
	}

}
