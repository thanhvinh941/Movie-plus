package com.movieplus.domain.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.controller.webui.MovieWebUIController;
import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.payload.request.GetInternalApiRequest;
import com.movieplus.domain.service.GenreTypeService;
import com.movieplus.domain.service.MovieWebUIService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieWebUIServiceImpl implements MovieWebUIService {
	
	private final GenreTypeService genreTypeService;
	
	@Override
	public List<MovieWebUIController.GenreType> getGenreType() {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		conditionStr = " genre_kbn = 1";
		apiRequest.setConditionStr(conditionStr);
		apiRequest.setOrderBys(List.of("order_score ASC"));
		try {
			List<GenreType> results = genreTypeService.getGenreType(apiRequest);
			return results.stream().map(g -> {
				MovieWebUIController.GenreType genreType = new MovieWebUIController.GenreType();
				BeanUtils.copyProperties(g, genreType);
				return genreType;
			}).collect(Collectors.toList());
		} catch (Exception e) {

		}
		return List.of();
	}

}
