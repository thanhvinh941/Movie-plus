package com.movieplus.domain.common.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.movieplus.config.security.view.View;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieInfoDto {
	private String id;
	private String movieName;
	private String movieSubName;
	private long durationMin;
	private String description;
	private String thumnail;
	private Long yearReleaseAt;
	private List<String> banners;
	private List<TrailerDto> trailers;
	private List<GenreTypeDto> genreType;
	private List<DirectorDto> directors;
	private List<StarInfoDto> starInfos;
	private ProductionInfoDto productionInfo;
	
	@JsonView(View.Admin.class)
	private LocalDateTime registTime;
	@JsonView(View.Admin.class)
	private LocalDateTime updateTime;
	@JsonView(View.Admin.class)
	private String updateUser;
	@JsonView(View.Admin.class)
	private Integer delFlg;
}
