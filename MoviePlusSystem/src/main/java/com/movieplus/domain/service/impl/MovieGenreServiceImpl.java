package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.db.read.RMovieGenreMapper;
import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.MovieGenreRepository;
import com.movieplus.domain.service.MovieGenreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieGenreServiceImpl implements MovieGenreService {
	
	private final MovieGenreRepository repository;
	private final RMovieGenreMapper movieGenreMapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<MovieGenre> records) throws Exception {
		try {
			List<MovieGenre> movieInfos = repository.saveAll(records);
			return movieInfos.stream()
					.map(MovieGenre::getId)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public List<MovieGenre> getMovieGenre(GetInternalApiRequest request) throws Exception {
		try {
			List<Map<String, Object>> results = movieGenreMapper.selectWhere(request.getConditionStr());
			List<MovieGenre> movieGenres = objectMapper.convertValue(results, new TypeReference<List<MovieGenre>>() {});
			return movieGenres;
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
