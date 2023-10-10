package com.movieplus.controller.external.operator;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.payload.response.PaginationResponse;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.config.common.util.GeneratorUtil.ExternalAPI.ExternalApiResponse;
import com.movieplus.controller.external.operator.dto.MovieInfoDto;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.service.GetMovieInfoListService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class GetMovieInfoListController {
	private final String[] logTitle = { "GetMovieInfoList" };
	private final ObjectMapper objectMapper;
	private final MessageManager messageManager;
	private final GetMovieInfoListService service;

	@Getter
	@Setter
	public static class GetMovieInfoListRequest {
		private int page;
		private int pageSize;
		private boolean ignoreDelFlg = false;
		private String searchTerm;
		private Map<String, String> orderBys;
	}

	@PostMapping("/getMovieInfoList")
	public ResponseEntity<ExternalApiResponse<PaginationResponse<MovieInfoDto>>> doGetMovieInfoList(
			@RequestBody String requestStr) {
		GetMovieInfoListRequest request = new GetMovieInfoListRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<GetMovieInfoListRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(errorMessage));
		}

		try {
			PaginationResponse<MovieInfoDto> response = new PaginationResponse<>();

			service.execute(request, response);

			return GeneratorUtil.ExternalAPI.createSuccessResponse(response);
		} catch (ClientException e) {
			log.error("{} ClientException fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(e.getMessage()));
		} catch (Exception e) {
			log.error("{} Exception fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorServerResponse(List.of(e.getMessage()));
		}
	}
}
