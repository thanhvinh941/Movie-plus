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
import com.movieplus.domain.entity.MovieTrailer;
import com.movieplus.domain.payload.request.InsertInternalApiRequest;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.MovieTrailerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal")
public class MovieTrailerInternalController {

	private String[] logTitle = {"MovieInfo_Internal"};
	private final MessageManager messageManager;
	private final MovieTrailerService movieTrailerService;
	
	@PostMapping("/insertMovieTrailer")
	@ResponseBody
	public String insertMovieTrailer(@RequestBody String requestStr) {
		InsertInternalApiRequest<MovieTrailer> request = new InsertInternalApiRequest<MovieTrailer>();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<InsertInternalApiRequest<MovieTrailer>>() {});
		} catch (Exception e) {
			log.error("{} insertMovieTrailer DecodeRequest fail: ", logTitle, e);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {

			List<String> results = movieTrailerService.save(request.getRecords());
			return GeneratorUtil.InternalAPI.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@PostMapping("/getMovieTrailer")
	@ResponseBody
	public String getMovieTrailer(@RequestBody String requestStr) {
		GetInternalApiRequest request = new GetInternalApiRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<GetInternalApiRequest>() {});
		} catch (Exception e) {
			log.error("{} getMovieTrailer DecodeRequest fail: ", logTitle, e);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {
			List<MovieTrailer> results = movieTrailerService.getMovieTrailer(request);
			return GeneratorUtil.InternalAPI.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
