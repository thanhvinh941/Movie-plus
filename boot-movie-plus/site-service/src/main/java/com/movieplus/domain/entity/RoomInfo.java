package com.movieplus.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

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
@Table(name = "room_info")
public class RoomInfo {
	@Id
	@GeneratedValue
	@UuidGenerator(style = Style.TIME)
	@Column(name = "id", updatable = false, nullable = false)
	private String id;

	@Column(nullable = false)
	private String siteId;

	@Column(nullable = false)
	private String roomName;

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
