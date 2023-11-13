package com.movieplus.config.common.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
			throws InternalAPIException, IOException {
		String url = getFullUrl(target, path);
		
		List<String> acceptList = new ArrayList<String>();
		acceptList.add("application/json");

		HttpHeaders headers = new HttpHeaders();
		headers.put("Content-Type", acceptList);
		headers.put(HttpHeaders.ACCEPT, acceptList);
		
		String requestJsonStr = initObjectMapper().writeValueAsString(requestObject);
		HttpEntity<String> requestEntity = new HttpEntity<String>(requestJsonStr, headers);
		ResponseEntity<ApiResponse> response = restTemplate.postForEntity(url, requestEntity, ApiResponse.class);
		if (response.getStatusCode() != HttpStatus.OK) {	
			throw new InternalAPIException(messageManager.getMessage("ERROR_INTERNAL", null));
		}
		ApiResponse exchange = response.getBody();
		if(exchange.getStatus() == 1 && exchange.getData() != null) {
			return initObjectMapper().convertValue(exchange.getData(), returnType);
		} else {
			log.error("ERROR CALL: {} with request:{}", path, objectMapper.writeValueAsString(requestObject));
			throw new InternalAPIException(messageManager.getMessage("ERROR_INTERNAL", null));
		}
	}

	private String getFullUrl(String target, String path) {
		String host = environment.getProperty(target);
		return host + "/" + path;
	}
	
	private ObjectMapper initObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
		mapper.setTimeZone(TimeZone.getDefault());
		return mapper;
	}
}
