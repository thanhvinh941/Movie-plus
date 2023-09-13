package com.movieplus.controller.external;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.payload.response.GetMovieListResponse;
import com.movieplus.domain.service.GetMovieInfoListService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class GetMovieInfoListController {

	private final String[] logTitle = { "GetMovieInfoList" };
	private final GetMovieInfoListService getMovieInfoListService;
	private final MessageManager messageManager;

	@Data
	public static class GetMovieListRequest {
		private List<String> genreTypeIds;
		private String searchTerm;
	}

	@PostMapping("/getMovieInfoList")
	public String getAllListMovies(@RequestBody String requestStr) {
		GetMovieListRequest request = new GetMovieListRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<GetMovieListRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}

		try {
			GetMovieListResponse response = new GetMovieListResponse();

			getMovieInfoListService.execute(request, response);

			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
