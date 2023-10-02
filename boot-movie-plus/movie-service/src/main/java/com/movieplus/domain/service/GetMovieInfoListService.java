package com.movieplus.domain.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.controller.external.operator.GetMovieInfoListController.GetMovieInfoListRequest;
import com.movieplus.controller.external.operator.dto.MovieInfoDto;
import com.movieplus.domain.common.CustomRepository;
import com.movieplus.domain.common.Util;
import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.entity.MovieInfo;
import com.movieplus.domain.payload.response.PaginationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetMovieInfoListService {

	private final CustomRepository<MovieInfo, String> movieInfoRepository;
	private final CustomRepository<MovieGenre, String> movieGenreRepository;
	private final CustomRepository<GenreType, String> genreTypeRepository;

	public void execute(GetMovieInfoListRequest request, PaginationResponse<MovieInfoDto> response) {
		Long totalRecords = getRotalRecordsMovieInfo(request);
		List<MovieInfo> movieInfos = getMovieInfoList(request);
		List<String> movieInfoIds = movieInfos.stream().map(MovieInfo::getId).toList();
		List<MovieGenre> movieGenres = getMovieGenreList(movieInfoIds);
		Set<String> genreIds = new HashSet<>();
		Map<String, List<MovieGenre>> movieGenreMap = new HashMap<>();
		movieGenres.forEach(mvg -> {
			genreIds.add(mvg.getGenreId());
			String movieId = mvg.getMovieId();
			if (movieGenreMap.containsKey(movieId)) {
				List<MovieGenre> mgs = movieGenreMap.get(movieId);
				mgs.add(mvg);
				movieGenreMap.put(movieId, mgs);
			} else {
				movieGenreMap.put(movieId, List.of(mvg));
			}
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
			List<MovieGenre> mGOfMovie = movieGenreMap.get(mvi.getId());
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

	private List<GenreType> getGenreTypeList(Set<String> genreIds) {
		String conditionStr = String.format(" id in (%s)", Util.buildQueryIn(genreIds));
		return genreTypeRepository.selectByCondition(GenreType.class, conditionStr, null, null, null, null, false);
	}

	private List<MovieGenre> getMovieGenreList(List<String> movieInfoIds) {
		String conditionStr = String.format(" movie_id in (%s)", Util.buildQueryIn(movieInfoIds));
		return movieGenreRepository.selectByCondition(MovieGenre.class, conditionStr, null, null, null, null, false);
	}

	private Long getRotalRecordsMovieInfo(GetMovieInfoListRequest request) {
		String conditionStr = buildQuery(request);
		return movieInfoRepository.count(MovieInfo.class, conditionStr);
	}

	private List<MovieInfo> getMovieInfoList(GetMovieInfoListRequest request) {
		String conditionStr = buildQuery(request);
		return movieInfoRepository.selectByCondition(MovieInfo.class, conditionStr, null, request.getOrderBys(),
				request.getPageSize(), request.getPage() * request.getPageSize(), false);
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
