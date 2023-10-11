package com.movieplus.domain.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieplus.config.common.constant.EndPointConstant;
import com.movieplus.config.common.repository.CustomRepository;
import com.movieplus.controller.external.operator.SettingChargeInfoController.SettingCharPlanInfoRequest;
import com.movieplus.domain.entity.ChargeInfoSet;
import com.movieplus.domain.repository.ChargeInfoSetRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettingCharPlanInfoService {

	private final CustomRepository customRepository;
	private final ObjectMapper objectMapper;

	public String execute(SettingCharPlanInfoRequest request) throws Exception {
		Map<String, Object> recordMap = new HashMap<>();
		recordMap.put("planName", request.getPlanName());
		recordMap.put("planKbn", request.getPlanKbn());
		recordMap.put("dayKbn", request.getDayKbn());
		recordMap.put("timeStart", request.getTimeStart());
		recordMap.put("timeEnd", request.getTimeEnd());

		String chargeInfoPlanId = customRepository
				.insertRecords(EndPointConstant.PriceService.Table.ChargeInfoPlan.name(), recordMap);

		for (Map.Entry<String, Integer> entry : request.getChargeInfoSet().entrySet()) {
			String key = entry.getKey();
			String[] idList = key.split("/");
			String movieId = idList[0];
			String seatId = idList[1];
			String siteId = idList[2];

			ChargeInfoSet chargeInfoSet = new ChargeInfoSet();
			chargeInfoSet.setMovieGradeId(movieId);
			chargeInfoSet.setSeatGradeId(seatId);
			chargeInfoSet.setSiteGradleId(siteId);
			chargeInfoSet.setPrice(entry.getValue());
			chargeInfoSet.setChargeInfoPlanId(chargeInfoPlanId);
			Map<String, Object> chargeInfoSetMap = new ObjectMapper().convertValue(chargeInfoSet,
					new TypeReference<Map<String, Object>>() {
					});
			chargeInfoSetMap.values().removeAll(Collections.singleton(null));
			customRepository.insertRecords(EndPointConstant.PriceService.Table.ChargeInfoSet.name(), chargeInfoSetMap);

		}

		return chargeInfoPlanId;
	}

}
