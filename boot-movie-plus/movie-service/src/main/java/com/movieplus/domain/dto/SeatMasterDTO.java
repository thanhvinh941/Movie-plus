package com.movieplus.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class SeatMasterDTO {
	private String id;
	private Integer seatRow;
	private Integer seatColume;
	private Integer seatSize;
	private Integer seatType;
}
