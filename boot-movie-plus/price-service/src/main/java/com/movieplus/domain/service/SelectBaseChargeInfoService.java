package com.movieplus.domain.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.movieplus.controller.external.operator.SelectChargeInfoController.SelectBaseChargeInfoRequest;
import com.movieplus.domain.entity.ChargeInfoSet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SelectBaseChargeInfoService {

	public Map<String, Integer> execute(SelectBaseChargeInfoRequest request) {
//		String conditionStr = String.format(" holiday_kbn = %d", request.getHolidayKbn());
//		List<ChargeInfoSet> chargeInfoSets = chargeInfoSetRepository.selectByCondition(ChargeInfoSet.class,
//				conditionStr, null, null, null, null, false);
//		return chargeInfoSets.stream().collect(
//				Collectors.toMap(cifs -> {
//							return cifs.getMovieGradeId() + "/" + cifs.getSeatGradeId() + "/" + cifs.getSiteGradleId();
//					}, 
//						ChargeInfoSet::getPrice)
//				);
		return null;
	}

}
