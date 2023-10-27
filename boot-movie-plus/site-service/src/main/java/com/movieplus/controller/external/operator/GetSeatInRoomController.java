package com.movieplus.controller.external.operator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.constant.EndPointConstant;
import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.payload.request.PaginationRequest;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.service.GetSeatInRoomService;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(EndPointConstant.EXTERNAL_PATH_URI + EndPointConstant.PREFIX_ADMIN )
public class GetSeatInRoomController {
	private final String[] logTitle = { "GetSeatInRoom" };
	private final MessageManager messageManager;
	private final GetSeatInRoomService service;
	
	@Getter
	@Setter
	public static class GetSeatInRoomRequest extends PaginationRequest{
		@NotNull
		private String id;
	}

	@Getter
	@Setter
	public static class RoomSeat {
		private String id;
		private SeatMaster seatMaster;
	}
	
	@Getter
	@Setter
	public static class SeatMaster {
		private String id;
		private Integer seatRow;
		private Integer seatColume;
		private Integer seatSize;
		private SeatGradle seatGradle;
	}
	
	@Getter
	@Setter
	public static class SeatGradle {
		private String id;
		private String seatGradeName;
	}
	
	@PostMapping("/getSeatInRoom")
	public ResponseEntity<?> GetSeatInRoom(@RequestBody String requestStr) throws JsonProcessingException {
		GetSeatInRoomRequest request = new GetSeatInRoomRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<GetSeatInRoomRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(errorMessage));
		}

		try {
			List<RoomSeat> response = new ArrayList<>();

			service.execute(request, response);

			return GeneratorUtil.ExternalAPI.createSuccessResponse(response);
		} catch (ClientException e) {
			log.error("{} ClientException fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(e.getMessage()));
		} catch (Exception e) {
			log.error("{} Exception fail: ", logTitle, e);
			return GeneratorUtil.ExternalAPI.createErrorServerResponse(List.of(e.getMessage()));
		}
	}
}
