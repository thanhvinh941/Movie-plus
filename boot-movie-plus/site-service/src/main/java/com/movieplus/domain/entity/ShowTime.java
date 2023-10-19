package com.movieplus.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import org.hibernate.tuple.GenerationTiming;

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
@Table(name="show_time")
public class ShowTime {
	
	@Id
    @GeneratedValue
    @UuidGenerator(style = Style.TIME)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	
	@Column(nullable = false)
	private String movieId;
	
	@Column(nullable = false)
	private String siteId;
	
	@Column(nullable = false)
	private String roomId;
	
	@CurrentTimestamp(timing = GenerationTiming.INSERT)
	@Column(nullable = false)
	private LocalDateTime startTime;
	
	@CurrentTimestamp(timing = GenerationTiming.INSERT)
	@Column(nullable = false)
	private LocalDateTime endTime;

	@CreationTimestamp
	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime registTime;

	@UpdateTimestamp
	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime updateTime;

	@Column(columnDefinition = "VARCHAR(255) default 'postman_update'")
	private String updateUser;

	@Column(columnDefinition = "integer default 0")
	private Byte delFlg;
}
