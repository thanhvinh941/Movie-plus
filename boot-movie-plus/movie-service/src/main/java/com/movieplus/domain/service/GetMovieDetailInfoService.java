package com.movieplus.domain.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.movieplus.config.common.repository.CustomRepository;
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
	private final CustomRepository customRepository;
	
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

	private List<GenreType> getGenreType(List<String> genresTypeIds) {
		String conditionStr = null;
		if (!genresTypeIds.isEmpty()) {
			conditionStr = String.format(" id in (%s)", GeneratorUtil.joiningListString(genresTypeIds));
		}
		try {
			return customRepository.selectByCondition(GenreType.class, conditionStr, Map.of("order_score" ,"ASC"), null, null, false);
		} catch (Exception e) {
			log.error("{} ERROR call getMovieGenre", CLASS_NAME, e);
		}
		return List.of();
	}

	private List<MovieTrailer> getMovieTrailer(String movieInfoId) {
		String conditionStr = String.format(" movie_id = '%s'", movieInfoId);
		try {
			return customRepository.selectByCondition(MovieTrailer.class, conditionStr);
		} catch (Exception e) {
			log.error("{} ERROR call getMovieGenre", CLASS_NAME, e);
		}
		return List.of();
	}

	private List<MovieGenre> getMovieGenre(String movieInfoId) {
		String conditionStr = String.format(" movie_id = '%s'", movieInfoId);
		try {
			return customRepository.selectByCondition(MovieGenre.class, conditionStr);
		} catch (Exception e) {
			log.error("{} ERROR call getMovieGenre", CLASS_NAME, e);
		}
		return List.of();
	}

	private List<MovieInfo> getMovieInfo(String movieId) {
		String conditionStr = String.format(" id = '%s'", movieId);
		try {
			return customRepository.selectByCondition(MovieInfo.class, conditionStr);
		} catch (Exception e) {
			log.error("{} ERROR call getMovieInfo", CLASS_NAME, e);
		}
		return List.of();
	}

}
