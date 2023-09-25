package com.movieplus.config;

import lombok.Data;

@Data
public class UserSession {
	private String id;
	private String username;
	private String fName;
	private String lName;
	private String email;
	private String imageUrl;
}
