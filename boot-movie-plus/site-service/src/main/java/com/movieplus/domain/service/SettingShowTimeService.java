package com.movieplus.domain.service;

import org.springframework.stereotype.Service;

import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.config.common.service.TransactionService;
import com.movieplus.config.common.util.XYZUtil;
import com.movieplus.controller.external.operator.SettingShowTimeController.SettingShowTimeRequest;
import com.movieplus.domain.entity.ShowTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettingShowTimeService extends TransactionService {

	private final CustomRepository customRepository;

	@Override
	public String getApiId() {
		return "SettingShowTime";
	}

	@Override
	public String getMicroServiceId() {
		return "site-service";
	}

	@Override
	public Object doProc(Object params) throws Exception {
		SettingShowTimeRequest request = (SettingShowTimeRequest) params;
		ShowTime showTime = new ShowTime();
		showTime.setMovieId(request.getMovieId());
		showTime.setSiteId(request.getSiteId());
		showTime.setRoomId(request.getRoomId());
		showTime.setStartTime(XYZUtil.convertToLocalDateTimeViaInstant(request.getStartTime()));
		showTime.setEndTime(XYZUtil.convertToLocalDateTimeViaInstant(request.getEndTime()));
		return customRepository.insertRecords(showTime);
	}

}
