package com.movieplus.config;

import lombok.Data;

@Data
public class UserCredentials{
	private String accessToken;
	private String id;
	private String email;
	private String name;
	private String imageUrl;
}
