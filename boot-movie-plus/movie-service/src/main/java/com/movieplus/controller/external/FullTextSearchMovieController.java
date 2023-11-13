package com.movieplus.controller.external;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.service.FullTextSearchMovieService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FullTextSearchMovieController {
	
	private final String[] logTitle = { "fullTextSearchMovie" };
	private final MessageManager messageManager;
	private final FullTextSearchMovieService service;
	
	@Getter
	@Setter
	public static class FullTextSearchMovieRequest{
		private String term;
	}
	
	@RequestMapping(path = "/fullTextSearchMovie", method = RequestMethod.POST)
	@ResponseBody
	public String settingRoomSeat(@RequestBody String requestStr) throws JsonProcessingException {
		FullTextSearchMovieRequest request = new FullTextSearchMovieRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<FullTextSearchMovieRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(errorMessage));
		}

		try {
			Object result = service.execute(request);
			return GeneratorUtil.InternalAPI.getResponseBodySuccess(result);
		} catch (ClientException e) {
			log.error("{} ClientException fail: ", logTitle, e);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(e.getMessage()));
		} catch (Exception e) {
			log.error("{} Exception fail: ", logTitle, e);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
