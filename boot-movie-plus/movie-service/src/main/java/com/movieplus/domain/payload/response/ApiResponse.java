package com.movieplus.domain.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class ApiResponse {

	private Object data;
	private int status;
	private List<String> errors;
}
