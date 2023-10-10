package com.movieplus.controller.external.operator;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.config.common.util.GeneratorUtil.ExternalAPI.ExternalApiResponse;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.common.dto.DirectorDto;
import com.movieplus.domain.common.dto.GenreTypeDto;
import com.movieplus.domain.common.dto.ProductionInfoDto;
import com.movieplus.domain.common.dto.StarInfoDto;
import com.movieplus.domain.common.dto.TrailerDto;
import com.movieplus.domain.service.GetMovieInfoService;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@PreAuthorize("hasAnyRole(ADMIN)")
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class A_RetrieveMovieInfoController {

	private final String[] logTitle = { "GetMovieInfo" };
	private final ObjectMapper objectMapper;
	private final MessageManager messageManager;
	private final GetMovieInfoService service;

	@Getter
	@Setter
	public static class RetrieveMovieInfoRequest {
		@NotNull
		private String movieId;
	}
	
	@Getter
	@Setter
	public static class RetrieveMovieInfoResponse{
		private String id;
		private String movieName;
		private String movieSubName;
		private long durationMin;
		private String description;
		private String thumnail;
		private Long yearReleaseAt;
		private List<String> banners;
		private List<TrailerDto> trailers;
		private List<GenreTypeDto> genreType;
		private List<DirectorDto> directors; // TODO add type of director
		private List<StarInfoDto> starInfos; // TODO add caster
		private ProductionInfoDto productionInfo;
	}

	@PostMapping("/GetMovieInfo")
	@ResponseBody
	public ResponseEntity<ExternalApiResponse<RetrieveMovieInfoResponse>> doGetMovieInfo(@RequestBody String requestStr) {
		RetrieveMovieInfoRequest request = new RetrieveMovieInfoRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<RetrieveMovieInfoRequest>() {});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(errorMessage));
		}

		try {
			RetrieveMovieInfoResponse response = new RetrieveMovieInfoResponse();

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
