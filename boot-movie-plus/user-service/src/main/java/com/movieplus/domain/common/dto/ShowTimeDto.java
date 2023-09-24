package com.movieplus.domain.common.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ShowTimeDto {
	private String id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;
	private RoomInfo roomInfo;
	
	@Getter
	@Setter
	public static class RoomInfo {
		private String id;
		private String roomName;
	}
}
