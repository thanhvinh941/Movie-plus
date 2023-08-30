package com.movieplus.controller.external;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.payload.EntryMovieInfoRequest;
import com.movieplus.domain.service.EntryMovieInfoService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class EntryMovieInfoController {

	private final EntryMovieInfoService entryMovieInfoService;
	
	@Getter
	@Setter
	public static class EntryMovieInfoResponse{
		private String movieId;
	}	
	
	@PostMapping("/EntryMovieInfo")
	public String doEntryMovieInfo(@Validated @RequestBody EntryMovieInfoRequest request) {
		EntryMovieInfoResponse response = new EntryMovieInfoResponse();
		try {
			entryMovieInfoService.execute(request, response);
			
			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
