package com.movieplus.domain.entity;

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
@Table(name="movie_star")
public class MovieStar {
	@Id
    @GeneratedValue
    @UuidGenerator(style = Style.TIME)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	
	@Column(nullable = false)
	private String movieId;
	
	@Column(nullable = false)
	private String starId;
	
	@Column(nullable = false)
	private String caster;
	
	private Integer sortNo;
}
