package com.movieplus.domain.entity;

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
@Table(name="movie_info")
public class MovieInfo {
	
	@Id
    @GeneratedValue
    @UuidGenerator(style = Style.TIME)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	
	@Column(nullable = false)
	private String movieName;
	
	private String movieSubName;
	
	@Column(nullable = false)
	private long durationMin;
	
	private String description;
	
	@Column(nullable = false)
	private String thumnail;

	@Column(nullable = false)
	private String productionId;
	
	private String movieGradeId;
	
	private String banners;
	
	@Column(nullable = false)
	private Long yearReleaseAt;

	@Column(nullable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registTime = LocalDateTime.now();
	
	@Column(nullable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime = LocalDateTime.now();
	
	@Column(nullable = false, columnDefinition = "VARCHAR(255) default 'postman_update'")
	private String updateUser = "postman_update";
	
	@Column(nullable = false, columnDefinition = "integer default 0")
	private Byte delFlg = 0;
}
