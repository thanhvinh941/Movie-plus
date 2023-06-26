package com.se.thanhvinh.simple.domain.payload.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LoginFaiedLogDto {
	private String id;
	private String email;
	private Integer tryCount;
	private LocalDateTime countUpStamp;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
}
