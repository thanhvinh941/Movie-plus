package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.ProductionInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface ProductionInfoService {

	List<String> save(List<ProductionInfo> records) throws Exception;

	List<ProductionInfo> getProduction(GetInternalApiRequest request) throws Exception;

}
