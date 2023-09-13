package com.movieplus.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class MovieInfoDTO {
	private String id;
	private String movieName;
	private String movieSubName;
	private long durationMin;
	private String description;
	private String thumnail;
	private String productionId;
	private Long yearReleaseAt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate registTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate updateTime;
}
