package com.movieplus.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreType {

	private String id;
	private String genreKbn;
	private String displayName;
	private String orderScore;
}