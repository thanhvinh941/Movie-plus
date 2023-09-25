package com.movieplus.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="user_info")
public class UserInfo {

	@Id
    @GeneratedValue
    @UuidGenerator(style = Style.TIME)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	@Column(nullable = false)
	private String username;
	
	private String fName;
	
	private String lName;
	
	@Column(columnDefinition = "integer default 0")
	private Byte isAdmin;
	
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	
	private String imageUrl;
	
	@Column(nullable = false)
	private Byte emailValidFlag;

	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registTime;
	
	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;
	
	@Column(columnDefinition = "VARCHAR(255) default 'postman_update'")
	private String updateUser;
	
	@Column(columnDefinition = "integer default 0")
	private Byte delFlg;
}
