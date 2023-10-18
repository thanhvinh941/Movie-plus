package com.movieplus.controller.external.operator;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.payload.response.RetrieveMovieInfoResponse;
import com.movieplus.domain.service.A_RetrieveSiteInfoService;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RetrieveSiteInfoDetailController {
	private final String[] logTitle = { "GetMovieInfo" };
	private final MessageManager messageManager;
	private final A_RetrieveSiteInfoService service;

	@Data
	public static class RetrieveSiteInfoRequest {
		@NotNull
		private String id;
	}

	@Getter
	@Setter
	public static class RetrieveSiteInfoResponse {
		private String id;
		private String siteName;
		private String localtion;
		private String siteGradleId;
		private String siteZipNo;
		private Double siteLonX;
		private Double siteLatY;
		private String accessInfo;
		private String information;
		private String notice;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime registTime;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime updateTime;
		private String updateUser;
		private Integer delFlg;
		private List<RoomInfo> roomInfos;

		@Getter
		@Setter
		public static class RoomInfo {
			private String id;
			private String roomName;
			@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
			private LocalDateTime registTime;
			@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
			private LocalDateTime updateTime;
			private String updateUser;
			private Integer delFlg;
		}
	}

	@PostMapping("/retrieveSiteInfo")
	public ResponseEntity<?> doGetMovieInfo(@RequestBody String requestStr) throws JsonProcessingException {
		RetrieveSiteInfoRequest request = new RetrieveSiteInfoRequest();
		// DecodeRequest
		try {
			request = new ObjectMapper().readValue(requestStr, new TypeReference<RetrieveSiteInfoRequest>() {
			});
		} catch (Exception e) {
			log.error("{} DecodeRequest fail: ", logTitle, e);
			String errorMessage = messageManager.getMessage("DECODE_FAIL", logTitle);
			return GeneratorUtil.ExternalAPI.createErrorClientResponse(List.of(errorMessage));
		}

		try {
			RetrieveSiteInfoResponse response = new RetrieveSiteInfoResponse();

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
