package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.SiteInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface SiteInfoService {

	List<String> save(List<SiteInfo> records) throws Exception;

	List<SiteInfo> getSiteInfo(GetInternalApiRequest request) throws Exception;

}
