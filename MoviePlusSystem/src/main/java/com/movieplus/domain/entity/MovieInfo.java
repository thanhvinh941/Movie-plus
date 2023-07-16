package com.movieplus.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieInfo {
	private String id;
	private String movieName;
	private String movieSubName;
	private String starrings;
	private String creators;
	private long durationMin;
	private String description;
	private String image;
	private String thumnail;
	private String productionId;
	private LocalDate realestAt;
	private LocalDateTime registAt;
	private LocalDateTime updateAt;
}
