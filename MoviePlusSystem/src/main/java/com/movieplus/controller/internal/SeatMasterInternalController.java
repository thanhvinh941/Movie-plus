package com.movieplus.controller.internal;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.entity.SeatMaster;
import com.movieplus.domain.payload.request.CreateInternalApiRequest;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.SeatMasterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/seat")
public class SeatMasterInternalController {

	private final SeatMasterService service;
	
	@PostMapping("/insertSeatMaster")
	@ResponseBody
	public String insertSiteInfo(@RequestBody CreateInternalApiRequest<SeatMaster> request) {
		try {
			List<String> results = service.save(request.getRecords());
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@PostMapping("/getSeatMaster")
	@ResponseBody
	public String getSeatMaster(@RequestBody GetInternalApiRequest request) {
		try {
			List<SeatMaster> results = service.getSeatMaster(request);
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
