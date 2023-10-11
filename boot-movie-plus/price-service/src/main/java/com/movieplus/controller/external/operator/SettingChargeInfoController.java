package com.movieplus.controller.external.operator;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.service.SettingCharPlanInfoService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SettingChargeInfoController {

	private final String[] logTitle = {"settingChargeInfo"}; 
	private final MessageManager messageManager;
	private final SettingCharPlanInfoService service;
	
	@Getter
	@Setter
	public static class SettingCharPlanInfoRequest{
		private String planName;
		private Integer planKbn;
		private Integer dayKbn;
		private Date timeStart;
		private Date timeEnd;
		private Map<String, Integer> chargeInfoSet;
	}
	
	@RequestMapping("/settingChargeInfo")
	@ResponseBody
	public String inserCharPlanInfo(@RequestBody String requestStr) {
		SettingCharPlanInfoRequest request = new SettingCharPlanInfoRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr,
					new TypeReference<SettingCharPlanInfoRequest>() {
					});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil
					.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {

			String result = service.execute(request);

			return GeneratorCommonUtil.getResponseBodySuccess(result);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
