package com.movieplus.controller.internal;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.entity.RoomSeat;
import com.movieplus.domain.payload.request.CreateInternalApiRequest;
import com.movieplus.domain.service.RoomSeatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal")
public class RoomSeatInternalController {
	private final RoomSeatService service;
	
	@PostMapping("/insertRoomSeat")
	@ResponseBody
	public String insertRoomSeat(@RequestBody CreateInternalApiRequest<RoomSeat> request) {
		try {
			List<String> results = service.save(request.getRecords());
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
