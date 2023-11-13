package com.movieplus.controller.external.member;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.payload.response.GetMovieInfoListByGenreTypeResponse;
import com.movieplus.domain.service.GetMovieInfoListByGenreTypeService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class GetMovieInfoListByGenreTypeController {

	private final String[] logTitle = { "GetMovieInfoList" };
	private final GetMovieInfoListByGenreTypeService getMovieInfoListService;
	private final MessageManager messageManager;

	@Data
	public static class GetMovieInfoListByGenreTypeRequest {
		private List<String> genreTypeIds;
		private String searchTerm;
	}

	@PostMapping("/getMovieInfoList")
	public String getAllListMovies(@RequestBody String requestStr) throws JsonProcessingException {
		GetMovieInfoListByGenreTypeRequest request = new GetMovieInfoListByGenreTypeRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<GetMovieInfoListByGenreTypeRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}

		try {
			GetMovieInfoListByGenreTypeResponse response = new GetMovieInfoListByGenreTypeResponse();

			getMovieInfoListService.execute(request, response);

			return GeneratorUtil.InternalAPI.getResponseBodySuccess(response);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
