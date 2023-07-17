package com.movieplus.domain.entity;

import java.time.LocalDate;

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
	private int yearReleaseAt;
	private LocalDate registAt;
	private LocalDate updateAt;
}
