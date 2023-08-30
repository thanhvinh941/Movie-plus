package com.movieplus.domain.common.dto;

import java.util.List;

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
}
