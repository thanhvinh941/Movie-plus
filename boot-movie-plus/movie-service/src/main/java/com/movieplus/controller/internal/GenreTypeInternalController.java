package com.movieplus.controller.internal;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.payload.request.CreateInternalApiRequest;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.GenreTypeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal")
public class GenreTypeInternalController {

	private String[] logTitle = {"GenreType_Internal"};
	private final MessageManager messageManager;
	private final GenreTypeService genreTypeService;

	@PostMapping("/insertGenreType")
	@ResponseBody
	public String insertMovie(@RequestBody String requestStr) {
		CreateInternalApiRequest<GenreType> request = new CreateInternalApiRequest<GenreType>();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<CreateInternalApiRequest<GenreType>>() {});
		} catch (Exception e) {
			log.error("{} insertMovie DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {
			List<String> results = genreTypeService.save(request.getRecords());
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			log.error("{} insertMovie fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
	
	@PostMapping("/getGenreType")
	@ResponseBody
	public String getGenreType(@RequestBody String requestStr) {
		GetInternalApiRequest request = new GetInternalApiRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<GetInternalApiRequest>() {});
		} catch (Exception e) {
			log.error("{} getGenreType DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		try {
			List<GenreType> results = genreTypeService.getGenreType(request);
			return GeneratorCommonUtil.getResponseBodySuccess(results);
		} catch (Exception e) {
			log.error("{} getGenreType fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
