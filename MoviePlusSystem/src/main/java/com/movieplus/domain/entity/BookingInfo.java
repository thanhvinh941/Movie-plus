package com.movieplus.domain.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="booking_info")
public class BookingInfo {

	private String id;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String showTimeId;
	
	@Column(nullable = false)
	private Integer bookingKbn;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registTime;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;
}
