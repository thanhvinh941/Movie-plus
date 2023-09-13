package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.StarInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface StarInfoService {

	List<StarInfo> getStarInfo(GetInternalApiRequest request) throws Exception;

	List<String> save(List<StarInfo> records) throws Exception;

}
