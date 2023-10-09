package com.movieplus.controller.external.operator;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.service.EntryMovieGradleService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EntryMovieGradleController {
	private final String[] logTitle = { "EntryMovieGradle" };
	private final ObjectMapper objectMapper;
	private final MessageManager messageManager;
	private final EntryMovieGradleService service;
	
	@Getter
	@Setter
	public static class EntryMovieGradleRequest{
		private List<String> movieGradeName;
	}
	
	@RequestMapping(path = "/entryMovieGradle", method = RequestMethod.POST)
	@ResponseBody
	public String doEntryMovieGradle(@RequestBody String requestStr) {
		EntryMovieGradleRequest request = new EntryMovieGradleRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<EntryMovieGradleRequest>() {});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}

		try {

			List<String> responses = service.execute(request);

			return GeneratorUtil.getResponseBodySuccess(responses);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
