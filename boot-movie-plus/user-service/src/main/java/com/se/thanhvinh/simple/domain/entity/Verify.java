package com.se.thanhvinh.simple.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "verifies")
public class Verify {

	@Id
    @GeneratedValue
    @UuidGenerator(style = Style.TIME)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	
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
