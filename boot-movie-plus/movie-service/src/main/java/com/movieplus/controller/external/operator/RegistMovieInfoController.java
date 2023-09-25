package com.movieplus.controller.external.operator;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.payload.EntryMovieInfoRequest;
import com.movieplus.domain.service.EntryMovieInfoService;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class RegistMovieInfoController {
	private final String[] logTitle = { "EntryMovieInfo" };
	private final EntryMovieInfoService entryMovieInfoService;
	private final MessageManager messageManager;
	@Getter
	@Setter
	public static class EntryMovieInfoResponse{
		@NotNull
		private String movieId;
	}	
	
	@PostMapping("/EntryMovieInfo")
	public String doEntryMovieInfo(@RequestBody String requestStr) {
		EntryMovieInfoRequest request = new EntryMovieInfoRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<EntryMovieInfoRequest>() {});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(messageManager.getMessage("DECODE_FAIL", logTitle)));
		}
		
		
		EntryMovieInfoResponse response = new EntryMovieInfoResponse();
		try {
			entryMovieInfoService.execute(request, response);
			
			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			log.error("{} execute fail: ", logTitle, e);
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
