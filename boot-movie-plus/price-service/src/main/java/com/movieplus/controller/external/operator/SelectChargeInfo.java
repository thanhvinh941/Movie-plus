package com.movieplus.controller.external.operator;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.service.SelectBaseChargeInfoService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("/api/admin")
@RequiredArgsConstructor
public class SelectChargeInfo {

	private final SelectBaseChargeInfoService service;
	private final String[] logTitle = {"RetrieveMovieInfo"}; 
	
	@Getter
	@Setter
	public static class SelectBaseChargeInfoResponse{
		private Map<String, String> siteGradleList;
		private Map<String, String> seatGradleList;
		private Map<String, String> movieGradleList;
		private Map<String, Map<String, Map<String, Integer>>> chargeMatrix;
	}
	
	@ResponseBody
	@RequestMapping(path = "/SelectBaseChargeInfo", method = RequestMethod.POST)
	public String doSelectChargeInfo() {
		try {
			SelectBaseChargeInfoResponse response = new SelectBaseChargeInfoResponse();
			
			service.execute(response);
			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
