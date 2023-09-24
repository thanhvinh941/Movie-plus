package com.movieplus.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class StarInfoDTO {
	private String id;
	private String starName;
	private String starDescription;
	private String starAvatar;
	private String nationality;
}
