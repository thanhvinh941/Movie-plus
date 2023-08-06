package com.movieplus.controller.internal;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.entity.SiteInfo;
import com.movieplus.domain.payload.request.CreateInternalApiRequest;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.SiteInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/site")
public class SiteInfoInternalController {

	private final SiteInfoService service;
	
	@PostMapping("/insertSiteInfo")
	@ResponseBody
	public String insertSiteInfo(@RequestBody CreateInternalApiRequest<SiteInfo> request) {
		try {
			List<String> results = service.save(request.getRecords());
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@PostMapping("/getSiteInfo")
	@ResponseBody
	public String getSiteInfo(@RequestBody GetInternalApiRequest request) {
		try {
			List<SiteInfo> results = service.getSiteInfo(request);
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
