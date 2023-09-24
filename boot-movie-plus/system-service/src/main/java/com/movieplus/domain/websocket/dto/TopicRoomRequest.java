package com.movieplus.domain.websocket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class TopicRoomRequest {
	private String id;//Seat Id
	private String seatKbn;
}
