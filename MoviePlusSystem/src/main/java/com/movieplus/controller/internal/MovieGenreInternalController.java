package com.movieplus.controller.internal;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.MovieGenreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/movie-genre")
public class MovieGenreInternalController {
	
	private final MovieGenreService movieGenreService;
	
	@PostMapping("/insertMovieGenre")
	@ResponseBody
	public String insertMovieGenre(List<MovieGenre> records) {
		try {
			List<String> results = movieGenreService.save(records);
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@PostMapping("/getMovieGenre")
	@ResponseBody
	public String getMovieGenre(@RequestBody GetInternalApiRequest request) {
		try {
			List<MovieGenre> results = movieGenreService.getMovieGenre(request);
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
