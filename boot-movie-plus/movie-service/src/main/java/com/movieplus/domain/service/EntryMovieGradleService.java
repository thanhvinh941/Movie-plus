package com.movieplus.domain.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.movieplus.controller.external.operator.EntryMovieGradleController.EntryMovieGradleRequest;
import com.movieplus.domain.entity.MovieGradle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntryMovieGradleService {public List<String> execute(EntryMovieGradleRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

//	private final JpaRepository<MovieGradle, String> repository;
//
//	public List<String> execute(EntryMovieGradleRequest request) {
//
//		List<MovieGradle> movieGradles = request.getMovieGradeName().stream().map(mgn -> {
//			MovieGradle movieGradle = new MovieGradle();
//			movieGradle.setMemberVisibleFlg(1);
//			movieGradle.setMovieGradeName(mgn);
//			return movieGradle;
//		}).toList();
//
//		List<MovieGradle> gradles = repository.saveAll(movieGradles);
//
//		return gradles.stream()
//				.map(MovieGradle::getId)
//				.toList();
//	}

}
