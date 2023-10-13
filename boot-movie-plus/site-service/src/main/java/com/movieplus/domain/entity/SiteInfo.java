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
@Table(name="site_info")
public class SiteInfo {

	@Id
    @GeneratedValue
    @UuidGenerator(style = Style.TIME)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	
	@Column(nullable = false)
	private String siteName;

	@Column(nullable = false)
	private String localtion;
	
	@Column(nullable = false)
	private String siteGradleId;
	
	@Column(nullable = false)
	private String siteZipNo;
	
	@Column(name = "site_lon_x" ,nullable = false)
	private Double siteLonX;
	
	@Column(name = "site_lat_y" ,nullable = false)
	private Double siteLatY;
	
	private String accessInfo;
	
	private String information;
	
	private String notice;

	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registTime;
	
	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;
	
	private String updateUser;
	
	@Column(columnDefinition = "integer default 0")
	private Integer delFlg;
}
