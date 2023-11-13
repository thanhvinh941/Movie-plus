package com.movieplus.domain.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarInfoDto {
	private String id;
	private String starName;
	private String starAvatar;
}
