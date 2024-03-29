package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperCommonUtil;
import com.movieplus.domain.db.read.RMovieInfoMapper;
import com.movieplus.domain.entity.MovieInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.MovieInfoRepository;
import com.movieplus.domain.service.MovieInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieInfoServiceImpl implements MovieInfoService {

	private final MovieInfoRepository repository;
	private final RMovieInfoMapper movieInfoMapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<MovieInfo> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperCommonUtil.writeValueAsString(records));
			List<MovieInfo> movieInfos = repository.saveAll(records);
			return movieInfos.stream()
					.map(MovieInfo::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

	@Override
	public List<MovieInfo> getMovieInfo(GetInternalApiRequest request) {
		try {
			log.info("Do getMovieInfo with request: {}", ObjectMapperCommonUtil.writeValueAsString(request));
			List<Map<String, Object>> results = movieInfoMapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<MovieInfo>>() {});
		} catch (Exception e) {
			log.error("ERROR getMovieInfo: {}", e);
			return List.of();
		}
	}

}
