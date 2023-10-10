package com.movieplus.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

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
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name ="charge_info_plan")
public class ChargeInfoPlan {
	@Id
    @GeneratedValue
    @UuidGenerator(style = Style.TIME)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	
	@Column(nullable = false)
	private String planName;
	
	@Column(columnDefinition = "integer default 0")
	private Integer planKbn;
	
	@Column(columnDefinition = "integer default 0")
	private Integer dayKbn;
	
	@Column(nullable = false)
	private LocalDateTime timeStart;
	
	private LocalDateTime timeEnd;
	
	@CreationTimestamp
	private LocalDateTime registTime;
	
	@UpdateTimestamp
	private LocalDateTime updateTime;
	
	@Column(columnDefinition = "VARCHAR(255) default 'postman_update'")
	private String updateUser;
	
	@Column(columnDefinition = "integer default 0")
	private Byte delFlg;
}
