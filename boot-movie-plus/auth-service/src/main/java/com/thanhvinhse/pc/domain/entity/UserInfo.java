package com.thanhvinhse.pc.domain.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
	private String id;
	private String name;
	private String email;
	private String imageUrl;
	private Boolean emailVerified;
	private String password;
	private Boolean validFlg;
	private Boolean delFlg;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
}
