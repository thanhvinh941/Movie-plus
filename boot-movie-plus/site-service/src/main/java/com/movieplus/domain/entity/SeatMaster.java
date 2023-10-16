package com.movieplus.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
@Table(name = "seat_master")
public class SeatMaster {

	@Id
	@GeneratedValue
	@UuidGenerator(style = Style.TIME)
	@Column(name = "id", updatable = false, nullable = false)
	private String id;

	@Column(nullable = false)
	private Integer seatRow;

	@Column(nullable = false)
	private Integer seatColume;

	@Column(nullable = false)
	private Integer seatSize;

	private String seatGradleId;

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
