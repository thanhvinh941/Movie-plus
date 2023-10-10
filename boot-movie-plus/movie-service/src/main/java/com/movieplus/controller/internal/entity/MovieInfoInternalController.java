package com.movieplus.controller.internal.entity;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.entity.MovieInfo;
import com.movieplus.domain.payload.request.InsertInternalApiRequest;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.MovieInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal")
public class MovieInfoInternalController {
	
	private String[] logTitle = {"MovieInfo_Internal"};
	private final MessageManager messageManager;
	private final MovieInfoService movieService;

	@PostMapping("/insertMovieInfo")
	@ResponseBody
	public String insertMovie(@RequestBody String requestStr) {
		InsertInternalApiRequest<MovieInfo> request = new InsertInternalApiRequest<MovieInfo>();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<InsertInternalApiRequest<MovieInfo>>() {});
		} catch (Exception e) {
			log.error("{} insertMovie DecodeRequest fail: ", logTitle, e);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {

			List<String> results = movieService.save(request.getRecords());
			return GeneratorUtil.InternalAPI.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@PostMapping("/getMovieInfo")
	@ResponseBody
	public String getMovie(@RequestBody String requestStr) {
		GetInternalApiRequest request = new GetInternalApiRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<GetInternalApiRequest>() {});
		} catch (Exception e) {
			log.error("{} getMovie DecodeRequest fail: ", logTitle, e);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {
			List<MovieInfo> results = movieService.getMovieInfo(request);
			return GeneratorUtil.InternalAPI.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
