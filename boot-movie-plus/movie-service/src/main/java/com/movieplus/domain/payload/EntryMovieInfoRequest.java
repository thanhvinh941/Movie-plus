package com.movieplus.domain.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntryMovieInfoRequest{
	private String movieName;
	private String movieSubName;
	private long durationMin;
	private String thumnail;
	private Long yearReleaseAt;
	private String description;
	private List<String> banners;
	private List<String> genreTypeIds;
	private String productionId;
	private List<Trailer> trailers;
//	private List<Caster> casters;
//	private List<DirectorClassify> directorClassifies;
	
	@Getter
	@Setter
	public class Trailer{
		private String trailerUrl;
		private String trailerTitle;
	}
	
//	@Getter
//	@Setter
//	public class Caster {
//		private String starId;
//		private String caster;
//	}
//	
//	@Getter
//	@Setter
//	public class DirectorClassify {
//		private String directorId;
//		private Byte directorKbn;
//	}
}
