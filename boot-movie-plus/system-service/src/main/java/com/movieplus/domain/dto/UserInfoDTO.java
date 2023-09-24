package com.movieplus.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class UserInfoDTO {
	private String id;
	private String username;
	private String fName;
	private String lName;
	private String email;
	private String password;
	private String imageUrl;
	private Byte emailValidFlag;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate registTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate updateTime;
}
