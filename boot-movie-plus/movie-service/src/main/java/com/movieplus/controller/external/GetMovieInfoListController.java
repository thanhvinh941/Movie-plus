package com.movieplus.controller.external;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.payload.response.GetMovieListResponse;
import com.movieplus.domain.service.GetMovieInfoListService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class GetMovieInfoListController {

	private final GetMovieInfoListService getMovieInfoListService;
	
	@Data
	public static class GetMovieListRequest{
		private List<String> genreTypeIds;
		private String searchTerm;
	}
	
	@PostMapping("/getMovieInfoList")
	public String getAllListMovies(@RequestBody GetMovieListRequest request) throws IOException {
		try {
			GetMovieListResponse response = new GetMovieListResponse();
			
			getMovieInfoListService.execute(request, response);
			
			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
