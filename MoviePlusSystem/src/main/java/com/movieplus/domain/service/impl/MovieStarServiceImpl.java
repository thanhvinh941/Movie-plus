package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.db.read.RMovieStarMapper;
import com.movieplus.domain.entity.MovieBanner;
import com.movieplus.domain.entity.MovieStar;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.MovieStarRepository;
import com.movieplus.domain.service.MovieStarService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieStarServiceImpl implements MovieStarService {
	
	private final MovieStarRepository repository;
	private final RMovieStarMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<MovieStar> records) throws Exception {
		try {
			List<MovieStar> movieStars = repository.saveAll(records);
			return movieStars.stream()
					.map(MovieStar::getId)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	@Override
	public List<MovieStar> getMovieStar(GetInternalApiRequest request) throws Exception {
		try {
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			List<MovieStar> movieStars = objectMapper.convertValue(results, new TypeReference<List<MovieStar>>() {});
			return movieStars;
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
