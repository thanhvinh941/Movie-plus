package com.movieplus.controller.internal.common;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.service.common.GetMovieDetailInfoService;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal")
public class GetMovieDetailInfoController {
	
	private String[] logTitle = {"COMMON_getMovieDetailInfo"};
	private final GetMovieDetailInfoService getMovieDetailInfoService;
	private final MessageManager messageManager;
	
	@Data
	public static class GetMovieDetailInfoRequest{
		@NotNull
		private String movieId;
	}
	
	@PostMapping("/getMovieDetailInfo")
	@ResponseBody
	public String getMovieDetailInfo(@RequestBody String requestStr) {
		GetMovieDetailInfoRequest request = new GetMovieDetailInfoRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<GetMovieDetailInfoRequest>() {});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {
			MovieDetailInfoDto result = getMovieDetailInfoService.getMovieDetailInfo(request.getMovieId());
			return GeneratorCommonUtil.getResponseBodySuccess(result);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
