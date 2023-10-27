package com.movieplus.domain.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreTypeDto {
	private String id;
	private String displayName;
}
