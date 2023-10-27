package com.movieplus.domain.common.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.movieplus.config.security.view.View;

import lombok.Data;

@Data
public class MovieDetailInfoDto {
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
	private List<DirectorDto> directors; // TODO add type of director
	private List<StarInfoDto> starInfos; // TODO add caster
	private ProductionInfoDto productionInfo;
}
