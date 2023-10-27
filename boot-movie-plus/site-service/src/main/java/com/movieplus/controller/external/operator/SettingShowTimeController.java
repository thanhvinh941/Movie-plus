package com.movieplus.controller.external.operator;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.constant.EndPointConstant;
import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.service.SettingShowTimeService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(EndPointConstant.EXTERNAL_PATH_URI + EndPointConstant.PREFIX_ADMIN )
public class SettingShowTimeController {

	private final String[] logTitle = { "SettingShowTime" };
	private final MessageManager messageManager;
	private final SettingShowTimeService service;
	
	@Getter
	@Setter
	public static class SettingShowTimeRequest{
		private String roomId;
		private String siteId;
		private String movieId;
		private Date startTime;
		private Date endTime;
	}
	
	@RequestMapping(path = "/settingShowTime", method = RequestMethod.POST)
	public ResponseEntity<?> settingRoomSeat(@RequestBody String requestStr) throws JsonProcessingException {
		SettingShowTimeRequest request = new SettingShowTimeRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<SettingShowTimeRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(errorMessage));
		}

		try {
			String result = (String) service.execute(request);
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
