package com.movieplus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.domain.common.CustomRepository;
import com.movieplus.domain.dto.MovieInfoDTO;
import com.movieplus.domain.entity.MovieInfo;

import lombok.RequiredArgsConstructor;

@SpringBootTest
class MovieServiceApplicationTests {

	@Autowired
	private CustomRepository<MovieInfoDTO, String> repository;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() throws JsonProcessingException {

		String conditionStr = " id = 'c0a802ad-8ad4-1e12-818a-d490219f0003'";
		List<String> fields = List.of("del_flg", "description", "duration_min", "movie_sub_name", "movie_name");
		List<?> results = repository.selectByCondition(MovieInfo.class, conditionStr, fields);
		List<Map<String, Object>> response = results.stream().map(r -> {
			List<?> result = objectMapper.convertValue(r, List.class);
			Map<String, Object> map = new HashMap<>();
			for (int i = 0; i < fields.size(); i++) {
				map.put(fields.get(i), result.get(i));
			}
			return map;
		}).toList();
		System.out.println(objectMapper.writeValueAsString(response));
	}

}
