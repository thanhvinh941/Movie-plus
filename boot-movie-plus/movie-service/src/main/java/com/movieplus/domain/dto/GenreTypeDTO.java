package com.movieplus.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class GenreTypeDTO {
	private String id;
	private Byte genreKbn;
	private String displayName;
	private Integer orderScore;
}
