package com.movieplus.controller.external.operator;

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
import com.movieplus.domain.service.SettingRoomSeatService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(EndPointConstant.EXTERNAL_PATH_URI + EndPointConstant.PREFIX_ADMIN )
public class SettingRoomSeatController {
	
	private final String[] logTitle = { "SettingRoomSeat" };
	private final MessageManager messageManager;
	private final SettingRoomSeatService service;
	
	@Getter
	@Setter
	public static class SettingRoomSeatRequest{
		private String roomId;
		private List<SeatMaster> seatMaster;
		
		@Getter
		@Setter
		public static class SeatMaster{
			private int seatRow;
			private int seatColume;
			private int seatSize;
			private String seatGradle;
			private Boolean seatEnable;
		}
	}
	
	@RequestMapping(path = "/settingRoomSeat", method = RequestMethod.POST)
	public ResponseEntity<?> settingRoomSeat(@RequestBody String requestStr) throws JsonProcessingException {
		SettingRoomSeatRequest request = new SettingRoomSeatRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<SettingRoomSeatRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(errorMessage));
		}

		try {
			Boolean result = (Boolean) service.execute(request);

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
