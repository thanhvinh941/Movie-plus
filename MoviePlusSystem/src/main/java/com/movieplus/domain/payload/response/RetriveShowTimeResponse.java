package com.movieplus.domain.payload.response;

import java.util.List;
import java.util.Map;

import com.movieplus.domain.common.dto.MovieDetailInfoDto;
import com.movieplus.domain.common.dto.ShowTimeDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetriveShowTimeResponse extends ShowTimeDto{
	private MovieDetailInfoDto movieDetailInfoDto;
	private List<Map<String, List<Seat>>> seatMatrix; //Map<row, List<seat>>
	
	@Getter
	@Setter
	public static class Seat{
		private String id;
		private int size;
		private int status;
	}
	
}
