package com.movieplus.controller.external.operator;

import java.util.List;

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
import com.movieplus.domain.payload.response.GetMovieInfoResponse;
import com.movieplus.domain.payload.response.PaginationResponse;
import com.movieplus.domain.service.GetMovieInfoService;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@PreAuthorize("hasAnyRole(ADMIN)")
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class GetMovieInfoController {

	private final String[] logTitle = { "GetMovieInfo" };
	private final ObjectMapper objectMapper;
	private final MessageManager messageManager;
	private final GetMovieInfoService service;

	@Data
	public static class GetMovieInfoRequest {
		@NotNull
		private String movieId;
	}

	@PostMapping("/GetMovieInfo")
	@ResponseBody
	public String doGetMovieInfo(@RequestBody String requestStr) {
		GetMovieInfoRequest request = new GetMovieInfoRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<GetMovieInfoRequest>() {});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}

		try {
			GetMovieInfoResponse response = new GetMovieInfoResponse();

			service.execute(request, response);

			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
