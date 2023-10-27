package com.movieplus.controller.external.operator;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.constant.EndPointConstant;
import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.payload.request.PaginationRequest;
import com.movieplus.config.common.payload.response.PaginationResponse;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.service.GetShowTimeOfRoomService;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(EndPointConstant.EXTERNAL_PATH_URI + EndPointConstant.PREFIX_ADMIN )
public class GetShowTimeOfRoomController {
	private final String[] logTitle = { "GetShowTimeOfRoom" };
	private final MessageManager messageManager;
	private final GetShowTimeOfRoomService service;
	
	@Getter
	@Setter
	public static class GetShowTimeOfRoomRequest extends PaginationRequest{
		@NotNull
		private String id;
	}

	@Getter
	@Setter
	public static class GetShowTimeOfRoomResponse extends PaginationResponse<ShowTime>{
	}

	@Getter
	@Setter
	public static class ShowTime {
		private String id;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime startTime;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime endTime;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime registTime;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime updateTime;
		private Object movieInfo;
		private String updateUser;
		private Byte delFlg;
	}
	
	@PostMapping("/getShowTimeOfRoom")
	public ResponseEntity<?> getShowTimeOfRoom(@RequestBody String requestStr) throws JsonProcessingException {
		GetShowTimeOfRoomRequest request = new GetShowTimeOfRoomRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<GetShowTimeOfRoomRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(errorMessage));
		}

		try {
			GetShowTimeOfRoomResponse response = new GetShowTimeOfRoomResponse();

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
