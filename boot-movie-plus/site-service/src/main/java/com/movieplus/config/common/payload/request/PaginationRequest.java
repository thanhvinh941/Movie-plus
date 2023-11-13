package com.movieplus.config.common.payload.request;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationRequest {
	private int page;
	private int pageSize;
	private boolean ignoreDelFlg = false;
	private Map<String, String> orderBys;
}
