package com.se.thanhvinh.simple.config;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VerifyDto {
	private String id;
	private String userId;
	private String token;
	private LocalDateTime expirationTime;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
}
