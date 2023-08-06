package com.movieplus.controller.internal;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.entity.MovieBanner;
import com.movieplus.domain.payload.request.CreateInternalApiRequest;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.MovieBannerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/movie-banner")
public class MovieBannerInternalController {

	private final MovieBannerService movieBannerService;
	
	@PostMapping("/insertMovieBanner")
	@ResponseBody
	public String insertMovieTrailer(@RequestBody CreateInternalApiRequest<MovieBanner> request) {
		try {

			List<String> results = movieBannerService.save(request.getRecords());
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@PostMapping("/getMovieBanner")
	@ResponseBody
	public String getMovieBanner(@RequestBody GetInternalApiRequest request) {
		try {
			List<MovieBanner> results = movieBannerService.getMovieBanner(request);
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
