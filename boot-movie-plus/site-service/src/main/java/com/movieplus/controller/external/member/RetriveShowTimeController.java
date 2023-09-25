package com.movieplus.controller.external.member;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.payload.response.RetriveShowTimeResponse;
import com.movieplus.domain.service.RetriveShowTimeService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class RetriveShowTimeController {
	
	@Getter
	@Setter
	public static class RetriveShowTimeRequest{
		private String showTimeId;
	}

	private final RetriveShowTimeService service;
	
	@RequestMapping("/retriveShowTime")
	@ResponseBody
	public String doRetriveShowTime(@RequestBody RetriveShowTimeRequest request) {
		try {
			RetriveShowTimeResponse response = new RetriveShowTimeResponse();
			service.execute(request, response);
			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
