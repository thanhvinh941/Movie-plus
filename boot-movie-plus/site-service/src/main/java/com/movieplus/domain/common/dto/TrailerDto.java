package com.movieplus.domain.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrailerDto{
	private String trailerUrl;
	private String trailerTitle;
}
