package com.movieplus.controller.internal;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.entity.ShowTime;
import com.movieplus.domain.payload.request.CreateInternalApiRequest;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.ShowTimeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal")
public class ShowTimeInternalController {

	private final ShowTimeService service;
	
	@PostMapping("/insertShowTime")
	@ResponseBody
	public String insertShowTime(@RequestBody CreateInternalApiRequest<ShowTime> request) {
		try {
			List<String> results = service.save(request.getRecords());
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@PostMapping("/getShowTime")
	@ResponseBody
	public String getShowTime(@RequestBody GetInternalApiRequest request) {
		try {
			List<ShowTime> results = service.getShowTime(request);
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
