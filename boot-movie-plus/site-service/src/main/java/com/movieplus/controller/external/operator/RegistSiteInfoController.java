package com.movieplus.controller.external.operator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.service.EntryCharPlanInfoService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class RegistSiteInfoController {

	private final String[] logTitle = {"RetrieveMovieInfo"}; 
	private final MessageManager messageManager;
	private final EntryCharPlanInfoService service;
	
	@Getter
	@Setter
	public static class EntryCharPlanInfoResponse{
		
	}
	
	@RequestMapping("/entryCharPlanInfo")
	public String inserCharPlanInfo(@RequestBody String requestStr) {
		Map<String, Integer> request = new HashMap<>();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr,
					new TypeReference<Map<String, Integer>>() {
					});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil
					.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {
			EntryCharPlanInfoResponse response = new EntryCharPlanInfoResponse();

			service.execute(request, response);

			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
