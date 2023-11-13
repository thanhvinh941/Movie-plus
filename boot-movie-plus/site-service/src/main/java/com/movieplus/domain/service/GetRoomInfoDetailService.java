package com.movieplus.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.movieplus.config.common.exception.ClientException;
import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.config.common.service.CallInternalAPIService;
import com.movieplus.controller.external.operator.GetRoomInfoDetailController.GetRoomInfoDetailRequest;
import com.movieplus.controller.external.operator.GetRoomInfoDetailController.GetRoomInfoDetailResponse;
import com.movieplus.domain.common.MessageManager;
import com.movieplus.domain.entity.RoomInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetRoomInfoDetailService {

	private final CustomRepository customRepository;
	private final MessageManager messageManager;
	
	public void execute(GetRoomInfoDetailRequest request, GetRoomInfoDetailResponse response) throws Exception {
		List<RoomInfo> roomInfos = getRoomInfo(request.getId());
		if(CollectionUtils.isEmpty(roomInfos)) {
			throw new ClientException(messageManager.getMessage("PARAM_INVALID", null));
		}
		BeanUtils.copyProperties(roomInfos.get(0), response);
	}

	private List<RoomInfo> getRoomInfo(String roomId) throws Exception {
		String roomInfoCondition = String.format("id = '%s' and del_flg = %d", roomId, 0);
		return customRepository.selectByCondition(RoomInfo.class, roomInfoCondition);
	}
}
