package com.movieplus.domain.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
	private String username;
	private String password;
	private Boolean isAdmin = false;
}
