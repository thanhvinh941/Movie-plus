package com.movieplus.domain.service;

import java.util.List;

import com.movieplus.domain.entity.RoomSeat;
import com.movieplus.domain.payload.request.GetInternalApiRequest;

public interface RoomSeatService {

	List<RoomSeat> getRoomSeat(GetInternalApiRequest apiRequest);

	List<String> save(List<RoomSeat> records) throws Exception;

}
