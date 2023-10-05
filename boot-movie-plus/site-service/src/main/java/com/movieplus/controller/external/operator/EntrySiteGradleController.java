package com.movieplus.controller.external.operator;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.service.EntrySiteGradleService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EntrySiteGradleController {
	private final String[] logTitle = { "EntrySiteGradle" };
	private final ObjectMapper objectMapper;
	private final MessageManager messageManager;
	private final EntrySiteGradleService service;
	
	@Getter
	@Setter
	public static class EntrySiteGradleRequest{
		private List<String> siteGradeName;
	}
	
	@RequestMapping(path = "/entrySiteGradle", method = RequestMethod.POST)
	@ResponseBody
	public String doEntryMovieGradle(@RequestBody String requestStr) {
		EntrySiteGradleRequest request = new EntrySiteGradleRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<EntrySiteGradleRequest>() {});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}

		try {

			List<String> responses = service.execute(request);

			return GeneratorCommonUtil.getResponseBodySuccess(responses);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
