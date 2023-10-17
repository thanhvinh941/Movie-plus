package com.movieplus.domain.service;

import java.beans.PropertyDescriptor;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.controller.external.operator.RetrieveSiteInfoDetailController.RetrieveSiteInfoRequest;
import com.movieplus.controller.external.operator.RetrieveSiteInfoDetailController.RetrieveSiteInfoResponse;
import com.movieplus.domain.entity.RoomInfo;
import com.movieplus.domain.entity.SiteInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class A_RetrieveSiteInfoService {

	private final ObjectMapper objectMapper;
	private final CustomRepository customRepository;

	public void execute(RetrieveSiteInfoRequest request, RetrieveSiteInfoResponse response) throws Exception {
		List<SiteInfo> siteInfoResults = customRepository.selectByCondition(SiteInfo.class,
				String.format("id = '%s'", request.getId()));
		List<RoomInfo> roomInfoResults = customRepository.selectByCondition(RoomInfo.class,
				String.format("site_id = '%s'", request.getId()));

		List<RetrieveSiteInfoResponse.RoomInfo> roomInfoResponse = roomInfoResults.stream().map(x -> {
			RetrieveSiteInfoResponse.RoomInfo roomInfo = new RetrieveSiteInfoResponse.RoomInfo();
			BeanUtils.copyProperties(x, roomInfo);
			return roomInfo;
		}).toList();
		BeanUtils.copyProperties(siteInfoResults.get(0), response);
		response.setRoomInfos(roomInfoResponse);
	}

}
