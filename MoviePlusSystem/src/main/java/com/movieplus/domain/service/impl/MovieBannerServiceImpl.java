package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.ObjectMapperCommonUtil;
import com.movieplus.domain.db.read.RMovieBannerMapper;
import com.movieplus.domain.entity.MovieBanner;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.repository.MovieBannerRepository;
import com.movieplus.domain.service.MovieBannerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieBannerServiceImpl implements MovieBannerService {
	
	private final MovieBannerRepository repository;
	private final RMovieBannerMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public List<String> save(List<MovieBanner> records) throws Exception {
		try {
			log.info("Do save with request: {}", ObjectMapperCommonUtil.writeValueAsString(records));
			List<MovieBanner> movieBanners = repository.saveAll(records);
			return movieBanners.stream()
					.map(MovieBanner::getId)
					.toList();
		} catch (Exception e) {
			log.error("ERROR save: {}", e);
			throw new Exception("Insert records fail");
		}
	}

	@Override
	public List<MovieBanner> getMovieBanner(GetInternalApiRequest request){
		try {
			log.info("Do getMovieBanner with request: {}", ObjectMapperCommonUtil.writeValueAsString(request));
			List<Map<String, Object>> results = mapper.selectWhere(request.getConditionStr(), request.getLimit(), request.getOffset(), request.getOrderBys());
			return objectMapper.convertValue(results, new TypeReference<List<MovieBanner>>() {});
		} catch (Exception e) {
			log.error("ERROR getMovieBanner: {}", e);
			return List.of();
		}
	}

}
