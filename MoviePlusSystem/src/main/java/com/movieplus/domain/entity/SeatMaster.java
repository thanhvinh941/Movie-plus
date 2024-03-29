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

	@Column(nullable = false, columnDefinition = "integer default 0")
	private Integer seatType;
}
