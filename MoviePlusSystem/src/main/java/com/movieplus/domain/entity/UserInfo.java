package com.movieplus.domain.entity;

import java.time.LocalDate;

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
	
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	
	private String imageUrl;
	
	@Column(nullable = false)
	private Byte isEmailVerify;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate registTime;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate updateTime;
}
