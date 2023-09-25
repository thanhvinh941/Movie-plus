package com.movieplus.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieplus.controller.external.operator.RegistMovieInfoController.EntryMovieInfoResponse;
import com.movieplus.domain.entity.MovieBanner;
import com.movieplus.domain.entity.MovieGenre;
import com.movieplus.domain.entity.MovieInfo;
import com.movieplus.domain.entity.MovieTrailer;
import com.movieplus.domain.payload.EntryMovieInfoRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntryMovieInfoService {
	
	private final MovieInfoService movieInfoService;
	private final MovieTrailerService movieTrailerService;
	private final MovieBannerService movieBannerService;
	private final MovieGenreService movieGenreService;
	
	private String movieId = null;
	
	@Transactional
	public void execute(EntryMovieInfoRequest request, EntryMovieInfoResponse response) throws Exception {
		MovieInfo movieInfo = new MovieInfo();
		BeanUtils.copyProperties(request, movieInfo);
		movieInfo.setRegistTime(LocalDateTime.now());
		movieInfo.setUpdateTime(LocalDateTime.now());
		try {
			synchronized(this){				
				movieId = movieInfoService.save(List.of(movieInfo)).get(0);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		List<MovieTrailer> movieTrailers = request.getTrailers().stream()
				.map((trailer) -> {
					MovieTrailer movieTrailer = new MovieTrailer();
					BeanUtils.copyProperties(trailer, movieTrailer);
					movieTrailer.setMovieId(movieId);
					return movieTrailer;
				})
				.toList();
		try {
			movieTrailerService.save(movieTrailers);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		List<MovieBanner> movieBanners = request.getBanners().stream()
				.map(banner -> {
					MovieBanner movieBanner = new MovieBanner();
					movieBanner.setBannerSrc(banner);
					movieBanner.setMovieId(movieId);
					return movieBanner;
				}).toList();
		try {
			movieBannerService.save(movieBanners);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		List<MovieGenre> movieGenres = request.getGenreTypeIds().stream()
				.map(genreTypeId -> {
					MovieGenre movieGenre = new MovieGenre();
					movieGenre.setGenreId(genreTypeId);
					movieGenre.setMovieId(movieId);
					return movieGenre;
				})
				.toList();
		try {
			movieGenreService.save(movieGenres);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
