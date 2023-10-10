package com.movieplus.domain.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.payload.response.PaginationResponse;
import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.config.common.util.XYZUtil;
import com.movieplus.controller.external.operator.GetMovieInfoListController.GetMovieInfoListRequest;
import com.movieplus.controller.external.operator.dto.MovieInfoDto;
import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.entity.MovieInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetMovieInfoListService {

	private final CustomRepository repository;
	private final ObjectMapper objectMapper;
	
	public void execute(GetMovieInfoListRequest request, PaginationResponse<MovieInfoDto> response) throws Exception {
		Long totalRecords = getRotalRecordsMovieInfo(request);
		List<MovieInfo> movieInfos = getMovieInfoList(request);
		List<String> movieInfoIds = movieInfos.stream().map(MovieInfo::getId).toList();
		List<MovieGenre> movieGenres = getMovieGenreList(movieInfoIds);
		Set<String> genreIds = new HashSet<>();
		Map<String, List<MovieGenre>> movieGenreMap = movieGenres.stream().collect(Collectors.groupingBy(MovieGenre::getMovieId));
		movieGenres.forEach(mvg -> {
			genreIds.add(mvg.getGenreId());
		});
		List<GenreType> genreTypes = getGenreTypeList(genreIds);
		Map<String, GenreType> genreTypeMap = genreTypes.stream()
				.collect(Collectors.toMap(GenreType::getId, Function.identity()));
		response.setPage(request.getPage());
		response.setPageSize(request.getPageSize());
		response.setTotalPages(Math.floorMod(totalRecords, request.getPageSize()));
		response.setTotalRecords(totalRecords.intValue());

		response.setRecords(movieInfos.stream().map(mvi -> {
			MovieInfoDto movieInfoDto = new MovieInfoDto();
			BeanUtils.copyProperties(mvi, movieInfoDto);
			List<MovieGenre> mGOfMovie = movieGenreMap.getOrDefault(mvi.getId(), List.of());
			movieInfoDto.setGenreTypes(mGOfMovie.stream().map(mvgomv -> {
				MovieInfoDto.MovieGenre mvmg = new MovieInfoDto.MovieGenre();
				BeanUtils.copyProperties(movieGenreMap, genreTypes);
				GenreType gt = genreTypeMap.get(mvgomv.getGenreId());
				mvmg.setGenreDisplayName(gt.getDisplayName());
				return mvmg;
			}).toList());
			return movieInfoDto;
		}).toList());
	}

	private List<GenreType> getGenreTypeList(Set<String> genreIds) throws Exception {
		String conditionStr = String.format(" id in (%s)", XYZUtil.buildQueryIn(genreIds));
		Object result = repository.selectByCondition(GenreType.class, conditionStr, null, null, null, false);
		return objectMapper.convertValue(result, new TypeReference<List<GenreType>>() {});
	}

	private List<MovieGenre> getMovieGenreList(List<String> movieInfoIds) throws Exception {
		String conditionStr = String.format(" movie_id in (%s)", XYZUtil.buildQueryIn(movieInfoIds));
		Object result = repository.selectByCondition(MovieGenre.class, conditionStr, null, null, null, false);
		return objectMapper.convertValue(result, new TypeReference<List<MovieGenre>>() {});
	}

	private Long getRotalRecordsMovieInfo(GetMovieInfoListRequest request) {
		String conditionStr = buildQuery(request);
		return repository.count(MovieInfo.class, conditionStr);
	}

	private List<MovieInfo> getMovieInfoList(GetMovieInfoListRequest request) throws Exception {
		String conditionStr = buildQuery(request);
		Object result = repository.selectByCondition(MovieInfo.class, conditionStr, null, request.getPageSize(), request.getPage() * request.getPageSize(), false);
		return objectMapper.convertValue(result, new TypeReference<List<MovieInfo>>() {});
	}

	private String buildQuery(GetMovieInfoListRequest request) {
		String conditionStr = "";
		if (!StringUtils.isBlank(request.getSearchTerm())) {
			conditionStr += String.format(" (movie_name like '%%%s%%' or movie_sub_name = '%%%s%%')",
					request.getSearchTerm(), request.getSearchTerm());
		}

		if (request.isIgnoreDelFlg()) {
			conditionStr += String.format(" and del_flg = %d", 0);
		}
		return conditionStr;
	}

}
