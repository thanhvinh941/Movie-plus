package com.movieplus.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.movieplus.controller.external.operator.EntryChargeInfoController.EntryCharPlanInfoRequest;
import com.movieplus.controller.external.operator.EntryChargeInfoController.EntryCharPlanInfoResponse;
import com.movieplus.domain.entity.ChargeInfoSet;
import com.movieplus.domain.repository.ChargeInfoSetRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntryCharPlanInfoService {

	private final ChargeInfoSetRepository chargeInfoSetRepository;
	
	public void execute(EntryCharPlanInfoRequest request, EntryCharPlanInfoResponse response) {
		List<ChargeInfoSet> chargeInfoSets = request.getRecords().entrySet().stream().map(entry->{
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
			chargeInfoSet.setHolidayKbn(request.getHolidayKbn());
			
			return chargeInfoSet;
		}).toList();
		chargeInfoSetRepository.saveAll(chargeInfoSets);
	}

}
