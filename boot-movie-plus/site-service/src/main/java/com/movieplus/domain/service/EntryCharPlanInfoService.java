package com.movieplus.domain.service;

import java.util.Map;

import com.movieplus.controller.external.operator.RegistSiteInfoController.EntryCharPlanInfoResponse;

public class EntryCharPlanInfoService {

	public void execute(Map<String, Integer> request, EntryCharPlanInfoResponse response) {
		for(Map.Entry<String, Integer> entry : request.entrySet()) {
			String key = entry.getKey();
			String[] idList = key.split("/");
		}
	}

}
