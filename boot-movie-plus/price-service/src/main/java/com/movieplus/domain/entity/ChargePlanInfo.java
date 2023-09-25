package com.movieplus.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Cacheable;
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
@Table(name ="Charge_plan_info")
public class ChargePlanInfo {
	@Id
    @GeneratedValue
    @UuidGenerator(style = Style.TIME)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	
	private String planCode;
	
	private String resultDiscountType; // minus or percent division
	
	private String resultDiscountSetId;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime planStartTime;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime planEndTime;
	
	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registTime;
	
	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;
	
	@Column(nullable = false)
	private String updateUser;
	
	@Column(nullable = false, columnDefinition = "integer default 0")
	private Integer delFlg;
}
