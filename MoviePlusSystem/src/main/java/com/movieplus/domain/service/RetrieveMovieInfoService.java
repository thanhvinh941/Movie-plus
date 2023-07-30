package com.movieplus.domain.service;

import org.springframework.stereotype.Service;

import com.movieplus.controller.RetrieveMovieInfoController.RetrieveMovieInfoRequest;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RetrieveMovieInfoService {

	@Data
	public static class RetrieveMovieInfoResponse{
		
	}
	
	public String execute(RetrieveMovieInfoRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
