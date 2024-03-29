package com.movieplus.domain.common.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonView;
import com.movieplus.config.security.view.View;

import lombok.Data;

@Data
public class BannerDto {
	private String bannerSrc;

	@JsonView(View.Admin.class)
	private String id;
	@JsonView(View.Admin.class)
	private LocalDateTime registTime;
	@JsonView(View.Admin.class)
	private LocalDateTime updateTime;
	@JsonView(View.Admin.class)
	private String updateUser;
	@JsonView(View.Admin.class)
	private Integer delFlg;
}
