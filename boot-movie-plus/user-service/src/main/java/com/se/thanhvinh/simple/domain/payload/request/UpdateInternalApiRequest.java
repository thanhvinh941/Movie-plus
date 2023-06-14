package com.se.thanhvinh.simple.domain.payload.request;

import lombok.Data;

@Data
public class UpdateInternalApiRequest {
	private String updateSet;
	private String conditionStr;
}
