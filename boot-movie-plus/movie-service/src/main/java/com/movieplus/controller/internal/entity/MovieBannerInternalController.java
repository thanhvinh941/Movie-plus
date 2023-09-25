package com.movieplus.controller.internal.entity;

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
import com.movieplus.domain.entity.MovieBanner;
import com.movieplus.domain.payload.request.InsertInternalApiRequest;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.MovieBannerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal")
public class MovieBannerInternalController {
	
	private String[] logTitle = {"MovieBanner_Internal"};
	private final MessageManager messageManager;
	private final MovieBannerService movieBannerService;
	
	@PostMapping("/insertMovieBanner")
	@ResponseBody
	public String insertMovieTrailer(@RequestBody String requestStr) {
		InsertInternalApiRequest<MovieBanner> request = new InsertInternalApiRequest<MovieBanner>();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<InsertInternalApiRequest<MovieBanner>>() {});
		} catch (Exception e) {
			log.error("{} insertMovieBanner DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {

			List<String> results = movieBannerService.save(request.getRecords());
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@PostMapping("/getMovieBanner")
	@ResponseBody
	public String getMovieBanner(@RequestBody String requestStr) {
		GetInternalApiRequest request = new GetInternalApiRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<GetInternalApiRequest>() {});
		} catch (Exception e) {
			log.error("{} getMovieBanner DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {
			List<MovieBanner> results = movieBannerService.getMovieBanner(request);
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
