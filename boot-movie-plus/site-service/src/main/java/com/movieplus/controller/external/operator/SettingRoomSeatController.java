package com.movieplus.controller.external.operator;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.controller.external.operator.GetRoomInfoDetailController.GetRoomInfoDetailRequest;
import com.movieplus.controller.external.operator.GetRoomInfoDetailController.GetRoomInfoDetailResponse;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.service.SettingRoomSeatService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SettingRoomSeatController {
	
	private final String[] logTitle = { "SettingRoomSeat" };
	private final ObjectMapper objectMapper;
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
	@ResponseBody
	public String settingRoomSeat(@RequestBody String requestStr) {
		SettingRoomSeatRequest request = new SettingRoomSeatRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<SettingRoomSeatRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.InternalAPI.getResponseBodyError(List.of(errorMessage));
		}

		try {

			boolean result = service.execute(request);

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
