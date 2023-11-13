package com.movieplus.domain.common.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonView;
import com.movieplus.config.security.view.View;

import lombok.Data;

@Data
public class GenreTypeDto {
	private String id;
	private String displayName;
}
