package com.movieplus.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="user_token")
public class UserToken {

	@Id
	private String refeshToken;

	@Column(nullable = false)
	private String accessToken;
	
	private long expirationTime; 
}
