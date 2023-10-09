package com.movieplus.domain.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieplus.domain.common.upload.UploadUtil;
import com.movieplus.domain.common.upload.UploadUtil.UploadFileRequest;
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
	private final MovieGenreService movieGenreService;
	private final UploadUtil uploadUtil;
	
	
	@Transactional
	public String execute(EntryMovieInfoRequest request) throws Exception {
		MovieInfo movieInfo = new MovieInfo();
		BeanUtils.copyProperties(request, movieInfo);
		
		movieInfo.setThumnail(StringUtils.isNoneEmpty(request.getThumnail()) ? getThumnail(request.getThumnail()) : "");
		movieInfo.setBanners(getBanners(request.getBanners()));
		movieInfo.setDelFlg(new Byte("0"));
		movieInfo.setUpdateUser("dummy user");
		String movieId = movieInfoService.save(List.of(movieInfo)).get(0);
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
		
		return movieId;
	}
	
	private String getThumnail(String thumnail) throws IOException {
		UploadFileRequest uploadFileRequest = new UploadFileRequest();
		uploadFileRequest.setFileBase64(thumnail);
		uploadFileRequest.setTargetFolder("thumnail");
		
		return uploadUtil.handleFileUpload(uploadFileRequest);
	}
	
	private String getBanners(List<String> bannersBase64s) throws Exception {
		String banners = "";
		for(String bannerBase64 : bannersBase64s) {
			UploadFileRequest uploadFileRequest = new UploadFileRequest();
			uploadFileRequest.setFileBase64(bannerBase64);
			uploadFileRequest.setTargetFolder("banner");
			try {
				banners += String.format("%s,", uploadUtil.handleFileUpload(uploadFileRequest)) ;
			} catch (IOException e) {
				throw new Exception(e);
			}
		}
		banners = StringUtils.removeEnd(banners, ",");
		System.out.println(banners);
		return banners;
	}

}
