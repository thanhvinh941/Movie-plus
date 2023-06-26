package com.se.thanhvinh.simple.domain.payload.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VerifyDto {
	private String id;
	private String userId;
	private String token;
	private Boolean validFlg;
	private LocalDateTime expirationTime;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
}
