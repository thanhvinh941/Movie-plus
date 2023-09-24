package com.movieplus.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class UserTokenDTO {
	private String refeshToken;
	private String accessToken;
	private long expirationTime; 
}
