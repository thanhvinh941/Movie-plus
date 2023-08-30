package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.ShowTime;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface ShowTimeService {

	List<String> save(List<ShowTime> records) throws Exception;

	List<ShowTime> getShowTime(GetInternalApiRequest request);

}
