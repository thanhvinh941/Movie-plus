package com.movieplus.domain.payload.request;

import java.util.List;

import lombok.Data;

@Data
public class GetInternalApiRequest {

	private String conditionStr;
	private int limit;
	private int offset;
	private List<String> orderBys;
}
