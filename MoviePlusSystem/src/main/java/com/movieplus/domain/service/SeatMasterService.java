package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.SeatMaster;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface SeatMasterService {

	List<SeatMaster> getSeatMaster(GetInternalApiRequest apiRequest);

	List<String> save(List<SeatMaster> records) throws Exception;

}
