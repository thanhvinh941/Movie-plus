package com.movieplus.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class RoomSeatDTO {
	private String id;
	private String seatId;
	private Integer usableStatus;
	private String roomId;
}
