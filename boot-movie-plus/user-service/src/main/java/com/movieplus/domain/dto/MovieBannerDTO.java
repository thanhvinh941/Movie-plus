package com.movieplus.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class MovieBannerDTO {
	private String id;
	private String movieId;
	private String bannerSrc;
}
