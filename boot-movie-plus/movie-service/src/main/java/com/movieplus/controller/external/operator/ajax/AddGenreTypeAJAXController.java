package com.movieplus.controller.external.operator.ajax;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.service.AJaxService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AddGenreTypeAJAXController {

	private final String[] logTitle = { "GetMovieInfoList" };
	private final MessageManager messageManager;
	private final AJaxService aJaxService;
	
	@Getter
	@Setter
	public static class AddGenreTypeAJAXRequest{
		private String movieId;
		private String genreTypeId;
	}
	
	@PostMapping("/doAddGenreType")
	@ResponseBody
	public String doAddGenreType(@RequestBody String requestStr) {
		AddGenreTypeAJAXRequest request = new AddGenreTypeAJAXRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<AddGenreTypeAJAXRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		String response = aJaxService.doAddGenreType(request);
		return response;
	}
	
	@PostMapping("/getAllGenreType")
	@ResponseBody
	public String getAllGenreType() throws JsonProcessingException {
		try {
			return GeneratorCommonUtil.getResponseBodySuccess(aJaxService.getAllGenreType());
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
