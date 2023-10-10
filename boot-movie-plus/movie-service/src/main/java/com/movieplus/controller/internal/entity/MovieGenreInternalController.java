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
import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.payload.request.InsertInternalApiRequest;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.MovieGenreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal")
public class MovieGenreInternalController {
	
	private String[] logTitle = {"MovieGenre_Internal"};
	private final MessageManager messageManager;
	private final MovieGenreService movieGenreService;
	
	@PostMapping("/insertMovieGenre")
	@ResponseBody
	public String insertMovieGenre(@RequestBody String requestStr) {
		InsertInternalApiRequest<MovieGenre> request = new InsertInternalApiRequest<MovieGenre>();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<InsertInternalApiRequest<MovieGenre>>() {});
		} catch (Exception e) {
			log.error("{} insertMovieGenre DecodeRequest fail: ", logTitle, e);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {
			List<String> results = movieGenreService.save(request.getRecords());
			return GeneratorUtil.InternalAPI.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@PostMapping("/getMovieGenre")
	@ResponseBody
	public String getMovieGenre(@RequestBody String requestStr) {
		GetInternalApiRequest request = new GetInternalApiRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<GetInternalApiRequest>() {});
		} catch (Exception e) {
			log.error("{} getMovieGenre DecodeRequest fail: ", logTitle, e);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {
			List<MovieGenre> results = movieGenreService.getMovieGenre(request);
			return GeneratorUtil.InternalAPI.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
