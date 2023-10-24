package com.movieplus.controller.external.operator;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.config.common.util.GeneratorUtil.ExternalAPI.ExternalApiResponse;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.payload.EntryMovieInfoRequest;
import com.movieplus.domain.service.EntryMovieInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EntryMovieInfoController {
	private final String[] logTitle = { "EntryMovieInfo" };
	private final EntryMovieInfoService entryMovieInfoService;
	private final MessageManager messageManager;

	@PostMapping("/entryMovieInfo")
	public ResponseEntity<ExternalApiResponse<String>> doEntryMovieInfo(@RequestBody String requestStr) {
		EntryMovieInfoRequest request = new EntryMovieInfoRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<EntryMovieInfoRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(errorMessage));
		}

		try {
			String result = entryMovieInfoService.execute(request);

			return GeneratorUtil.ExternalAPI.createSuccessResponse(result);
		} catch (ClientException e) {
			log.error("{} ClientException fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(e.getMessage()));
		} catch (Exception e) {
			log.error("{} Exception fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorServerResponse(List.of(e.getMessage()));
		}
	}
}
