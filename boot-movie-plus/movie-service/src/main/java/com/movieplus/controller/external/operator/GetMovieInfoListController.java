package com.movieplus.controller.external.operator;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.entity.MovieInfo;
import com.movieplus.domain.payload.response.PaginationResponse;
import com.movieplus.domain.service.GetMovieInfoListService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
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

	@PostMapping("/GetMovieInfoList")
	@ResponseBody
	public String doGetMovieInfoList(@RequestBody String requestStr) {
		GetMovieInfoListRequest request = new GetMovieInfoListRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<GetMovieInfoListRequest>() {});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}

		try {
			PaginationResponse<MovieInfo> response = new PaginationResponse<>();

			service.execute(request, response);

			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
