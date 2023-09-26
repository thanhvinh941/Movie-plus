package com.movieplus.domain.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class PaginationResponse<T> {
	private int page;
	private int pageSize;
	private int totalPages;
	private int totalRecords;
	private List<T> records;
}
