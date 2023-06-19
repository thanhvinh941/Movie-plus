package com.se.thanhvinh.simple.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users", 
		indexes = { @Index(name = "auth_user_index", columnList = "id, email", unique = true)})
public class User {

	@Id
    @GeneratedValue
    @UuidGenerator(style = Style.TIME)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	private String imageUrl;
	
	@Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
	private Boolean emailVerified;

	@JsonIgnore
	private String password;
	
	@Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    private Boolean validFlg;
	
	@Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    private Boolean delFlg;
	
	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createAt;
	
	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime updateAt;
}
