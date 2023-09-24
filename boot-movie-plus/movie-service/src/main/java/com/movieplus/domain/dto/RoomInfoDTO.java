package com.movieplus.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class RoomInfoDTO {
	private String id;
	private String siteId;
	private String roomName;
}
