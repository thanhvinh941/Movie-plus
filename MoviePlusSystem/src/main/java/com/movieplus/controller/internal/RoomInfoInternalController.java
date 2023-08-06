package com.movieplus.controller.internal;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.entity.RoomInfo;
import com.movieplus.domain.payload.request.CreateInternalApiRequest;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.RoomInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/room")
public class RoomInfoInternalController {

	private final RoomInfoService service;
	
	@PostMapping("/insertRoomInfo")
	@ResponseBody
	public String insertRoomInfo(@RequestBody CreateInternalApiRequest<RoomInfo> request) {
		try {
			List<String> results = service.save(request.getRecords());
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@PostMapping("/getRoomInfo")
	@ResponseBody
	public String getRoomInfo(@RequestBody GetInternalApiRequest request) {
		try {
			List<RoomInfo> results = service.getRoomInfo(request);
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
