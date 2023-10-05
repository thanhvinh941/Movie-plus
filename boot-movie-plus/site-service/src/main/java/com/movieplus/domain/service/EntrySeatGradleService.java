package com.movieplus.domain.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.movieplus.controller.external.operator.EntrySeatGradleController.EntrySeatGradleRequest;
import com.movieplus.domain.entity.SeatGradle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntrySeatGradleService {
	
	private final JpaRepository<SeatGradle, String> repository;

	public List<String> execute(EntrySeatGradleRequest request) {
		List<SeatGradle> seatGradles = request.getSeatGradeName().stream().map(mgn -> {
			SeatGradle seatGradle = new SeatGradle();
			seatGradle.setMemberVisibleFlg(1);
			seatGradle.setSeatGradeName(mgn);
			return seatGradle;
		}).toList();

		List<SeatGradle> gradles = repository.saveAll(seatGradles);

		return gradles.stream()
				.map(SeatGradle::getId)
				.toList();
	}

}
