package com.se.thanhvinh.simple.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users_verifies",
	indexes = { @Index(name = "users_verifies_index", columnList = "id, user_id", unique = false),
			@Index(name = "verifies_index", columnList = "token", unique = true)})
public class UserVerify {

	@Id
    @GeneratedValue
    @UuidGenerator(style = Style.TIME)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	
	@Column(nullable = false)
	private String userId;
	
	@GeneratedValue
    @UuidGenerator(style = Style.TIME)
	@Column(name = "token", updatable = false, nullable = false)
	private String token;
	
	@Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    private Boolean validFlg;
	
	private LocalDateTime expirationTime;
	
	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createAt;
	
	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime updateAt;
}