package com.movieplus.config.common.service;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.util.GeneratorUtil.InternalAPI.ApiResponse;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.common.exception.InternalAPIException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CallInternalAPIService {
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	private final Environment environment;
	private final MessageManager messageManager; 

	public static final String MOVIE_SERVICE = "micro-service.movie-service";
	public static final String USER_SERVICE = "micro-service.user-service";
	public static final String SITE_SERVICE = "micro-service.site-service";
	public static final String BOOKING_SERVICE = "micro-service.booking-service";
	public static final String SYSTEM_SERVICE = "micro-service.system-service";

	public <T> T callPostMenthodForObject(Object requestObject, String target, String path, TypeReference<T> returnType)
			throws JsonProcessingException, InternalAPIException {
		String url = getFullUrl(target, path);
		String requestJson = objectMapper.writeValueAsString(requestObject);

		HttpEntity<String> request = new HttpEntity<String>(requestJson, getJsonHeaders());
		ResponseEntity<ApiResponse> restExchange = restTemplate.postForEntity(url, request, ApiResponse.class);
		if(restExchange.getBody() != null && restExchange.getBody().getStatus() == 1 && restExchange.getBody().getData() != null) {
			return objectMapper.convertValue(restExchange.getBody().getData(), returnType);
		} else {
			log.error("ERROR CALL: {} with request:{}", path, objectMapper.writeValueAsString(requestObject));
			throw new InternalAPIException(messageManager.getMessage("ERROR_INTERNAL", null));
		}
	}

	private String getFullUrl(String target, String path) {
		String host = environment.getProperty(target);
		return host + "/" + path;
	}

	private HttpHeaders getJsonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
