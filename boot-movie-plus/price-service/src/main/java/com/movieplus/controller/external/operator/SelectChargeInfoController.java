package com.movieplus.controller.external.operator;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.controller.external.operator.EntryChargeInfoController.EntryCharPlanInfoRequest;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.service.SelectBaseChargeInfoService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("/api/admin")
@RequiredArgsConstructor
public class SelectChargeInfoController {

	private final SelectBaseChargeInfoService service;
	private final MessageManager messageManager;
	private final String[] logTitle = { "selectBaseChargeInfo" };

	@Getter
	@Setter
	public static class SelectBaseChargeInfoRequest {
		private Integer holidayKbn;
	}

	@ResponseBody
	@RequestMapping(path = "/selectBaseChargeInfo", method = RequestMethod.POST)
	public String doSelectChargeInfo(@RequestBody String requestStr) {
		SelectBaseChargeInfoRequest request = new SelectBaseChargeInfoRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<SelectBaseChargeInfoRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil
					.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}

		try {
			Map<String, Integer> results = service.execute(request);
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
