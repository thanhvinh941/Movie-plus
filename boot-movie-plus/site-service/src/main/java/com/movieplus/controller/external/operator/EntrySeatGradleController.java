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
import com.movieplus.domain.service.EntrySeatGradleService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EntrySeatGradleController {
	private final String[] logTitle = { "EntrySeatGradle" };
	private final ObjectMapper objectMapper;
	private final MessageManager messageManager;
	private final EntrySeatGradleService service;
	
	@Getter
	@Setter
	public static class EntrySeatGradleRequest{
		private List<String> seatGradeName;
	}
	
	@RequestMapping(path = "/entrySeatGradle", method = RequestMethod.POST)
	@ResponseBody
	public String doEntryMovieGradle(@RequestBody String requestStr) {
		EntrySeatGradleRequest request = new EntrySeatGradleRequest();
		// DecodeRequest
		try {
			request = objectMapper.readValue(requestStr, new TypeReference<EntrySeatGradleRequest>() {});
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
