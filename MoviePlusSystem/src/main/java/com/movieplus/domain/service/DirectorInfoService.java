package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.DirectorInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface DirectorInfoService {

	List<DirectorInfo> getDirectorInfo(GetInternalApiRequest request);

	List<String> save(List<DirectorInfo> records) throws Exception;

}
