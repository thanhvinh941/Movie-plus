package com.movieplus.controller.external;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.payload.response.RetrieveMovieInfoResponse;
import com.movieplus.domain.service.RetrieveMovieInfoService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class RetrieveMovieInfoController {
	
	@Data
	public static class RetrieveMovieInfoRequest{
		private String movieId;
	}
	
	private final RetrieveMovieInfoService service;
	
	@PostMapping("/retrieveMovieInfo")
	@ResponseBody
	public String doRetrieveMovieInfo(@RequestBody RetrieveMovieInfoRequest request) {
		try {
			RetrieveMovieInfoResponse response = new RetrieveMovieInfoResponse();
			
			service.execute(request, response);
			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
