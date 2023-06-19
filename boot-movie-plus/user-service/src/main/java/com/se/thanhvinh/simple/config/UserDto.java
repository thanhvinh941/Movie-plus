package com.se.thanhvinh.simple.config;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {
	private String id;
	private String name;
	private String email;
	private String imageUrl;
	private Boolean emailVerified;
	private String password;
	private Boolean delFlg;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
}
