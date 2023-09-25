package com.movieplus.controller.external.member;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.controller.internal.common.GetMovieDetailInfoController.GetMovieDetailInfoRequest;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.payload.response.RetrieveMovieInfoResponse;
import com.movieplus.domain.service.RetrieveMovieInfoService;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class RetrieveMovieInfoController {
	
	private final String[] logTitle = {"RetrieveMovieInfo"}; 
	private final RetrieveMovieInfoService service;
	private final MessageManager messageManager;

	@Data
	public static class RetrieveMovieInfoRequest{
		@NotNull
		private String movieId;
	}
	
	@PostMapping("/retrieveMovieInfo")
	@ResponseBody
	public String doRetrieveMovieInfo(@RequestBody String requestStr) {
		RetrieveMovieInfoRequest request = new RetrieveMovieInfoRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<RetrieveMovieInfoRequest>() {});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {
			RetrieveMovieInfoResponse response = new RetrieveMovieInfoResponse();
			
			service.execute(request, response);
			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
