package com.movieplus.domain.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.config.common.util.GeneratorUtil;
import com.movieplus.domain.common.dto.DirectorDto;
import com.movieplus.domain.common.dto.GenreTypeDto;
import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.dto.StarInfoDto;
import com.movieplus.domain.common.dto.TrailerDto;
import com.movieplus.domain.entity.GenreType;
import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.entity.MovieInfo;
import com.movieplus.domain.entity.MovieTrailer;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetMovieDetailInfoService {

	private final String CLASS_NAME = "GetMovieDetailInfoService";
	private final GenreTypeService genreTypeService;
	private final MovieInfoService movieInfoService;
	private final MovieGenreService movieGenreService;
	private final MovieTrailerService movieTrailerService;

	public MovieDetailInfoDto getMovieDetailInfo(String movieId) {
		MovieDetailInfoDto movieDetailInfoDto = new MovieDetailInfoDto();
		List<MovieInfo> movieInfos = getMovieInfo(movieId);
		if (movieInfos.isEmpty()) {

		}

		MovieInfo movieInfo = movieInfos.get(0);
		String movieInfoId = movieInfo.getId();

		List<MovieTrailer> movieTrailers = getMovieTrailer(movieInfoId);
		List<TrailerDto> responseTrailers = movieTrailers.stream().map(t -> {
			TrailerDto trailer = new TrailerDto();
			BeanUtils.copyProperties(t, trailer);
			return trailer;
		}).toList();

		List<MovieGenre> movieGenres = getMovieGenre(movieInfoId);
		List<String> genreIds = movieGenres.stream().map(MovieGenre::getGenreId).toList();
		List<GenreType> genreTypes = getGenreType(genreIds);
		List<GenreTypeDto> responseGenreTypes = genreTypes.stream().map(g -> {
			GenreTypeDto genreType = new GenreTypeDto();
			BeanUtils.copyProperties(g, genreType);
			return genreType;
		}).toList();

		return generatorResponse(movieDetailInfoDto, movieInfo, null, null, 
				responseTrailers, responseGenreTypes);
	}

	private MovieDetailInfoDto generatorResponse(MovieDetailInfoDto movieDetailInfoDto, MovieInfo movieInfo,
			List<DirectorDto> responseDirectorInfo, List<StarInfoDto> responseStar,
			List<TrailerDto> responseTrailers, List<GenreTypeDto> responseGenreTypes) {
		BeanUtils.copyProperties(movieInfo, movieDetailInfoDto);
		movieDetailInfoDto.setTrailers(responseTrailers);
		movieDetailInfoDto.setGenreType(responseGenreTypes);
		movieDetailInfoDto.setDirectors(responseDirectorInfo);
		movieDetailInfoDto.setStarInfos(responseStar);
		return movieDetailInfoDto;
	}

//	private List<StarInfoDTO> getStarInfo(List<String> starIds) {
//		GetInternalApiRequest apiRequest = new GetInternalApiRequest();
//
//		String conditionStr = null;
//		if (!starIds.isEmpty()) {
//			conditionStr = String.format(" id in (%s)", GeneratorCommonUtil.joiningListString(starIds));
//		}
//		apiRequest.setConditionStr(conditionStr);
//		try {
//			return starInfoService.getStarInfo(apiRequest);
//		} catch (Exception e) {
//			log.error("{} ERROR call getStarInfo", CLASS_NAME, e);
//		}
//		return List.of();
//	}

//	private List<DirectorInfoDTO> getDirectorInfo(List<String> directorIds) {
//		GetInternalApiRequest apiRequest = new GetInternalApiRequest();
//
//		String conditionStr = null;
//		if (!directorIds.isEmpty()) {
//			conditionStr = String.format(" id in (%s)", GeneratorCommonUtil.joiningListString(directorIds));
//		}
//		apiRequest.setConditionStr(conditionStr);
//		try {
//			return directorInfoService.getDirectorInfo(apiRequest);
//		} catch (Exception e) {
//			log.error("{} ERROR call getGenreType", CLASS_NAME, e);
//		}
//		return List.of();
//	}

	private List<GenreType> getGenreType(List<String> genresTypeIds) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		if (!genresTypeIds.isEmpty()) {
			conditionStr = String.format(" id in (%s)", GeneratorUtil.joiningListString(genresTypeIds));
		}
		apiRequest.setConditionStr(conditionStr);
		apiRequest.setOrderBys(Map.of("order_score" ,"ASC"));
		try {
			return genreTypeService.getGenreType(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getGenreType", CLASS_NAME, e);
		}
		return List.of();
	}

//	private List<MovieStar> getMovieStar(String movieInfoId) {
//		GetInternalApiRequest apiRequest = new GetInternalApiRequest();
//
//		String conditionStr = null;
//		conditionStr = String.format(" movie_id = '%s'", movieInfoId);
//		apiRequest.setConditionStr(conditionStr);
//
//		try {
//			return movieStarService.getMovieStar(apiRequest);
//		} catch (Exception e) {
//			log.error("{} ERROR call getMovieStar", CLASS_NAME, e);
//		}
//		return List.of();
//	}
//
//	private List<MovieDirector> getMovieDirector(String movieInfoId) {
//		GetInternalApiRequest apiRequest = new GetInternalApiRequest();
//
//		String conditionStr = null;
//		conditionStr = String.format(" movie_id = '%s'", movieInfoId);
//		apiRequest.setConditionStr(conditionStr);
//
//		try {
//			return movieDirectorService.getMovieDirector(apiRequest);
//		} catch (Exception e) {
//			log.error("{} ERROR call getMovieDirector", CLASS_NAME, e);
//		}
//		return List.of();
//	}

	private List<MovieTrailer> getMovieTrailer(String movieInfoId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		conditionStr = String.format(" movie_id = '%s'", movieInfoId);
		apiRequest.setConditionStr(conditionStr);

		try {
			return movieTrailerService.getMovieTrailer(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getMovieTrailer", CLASS_NAME, e);
		}
		return List.of();
	}

	private List<MovieGenre> getMovieGenre(String movieInfoId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = String.format(" movie_id = '%s'", movieInfoId);
		apiRequest.setConditionStr(conditionStr);
		try {
			return movieGenreService.getMovieGenre(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getMovieGenre", CLASS_NAME, e);
		}
		return List.of();
	}

	private List<MovieInfo> getMovieInfo(String movieId) {
		GetInternalApiRequest apiRequest = new GetInternalApiRequest();

		String conditionStr = null;
		conditionStr = String.format(" id = '%s'", movieId);
		apiRequest.setConditionStr(conditionStr);

		try {
			return movieInfoService.getMovieInfo(apiRequest);
		} catch (Exception e) {
			log.error("{} ERROR call getMovieInfo", CLASS_NAME, e);
		}
		return List.of();
	}

}
