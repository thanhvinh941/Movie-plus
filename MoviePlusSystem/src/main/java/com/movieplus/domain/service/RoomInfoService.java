package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.RoomInfo;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface RoomInfoService {

	List<RoomInfo> getRoomInfo(GetInternalApiRequest request) throws Exception;

	List<String> save(List<RoomInfo> records) throws Exception;

}
