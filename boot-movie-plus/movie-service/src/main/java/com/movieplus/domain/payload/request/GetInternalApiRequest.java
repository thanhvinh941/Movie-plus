package com.movieplus.domain.payload.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetInternalApiRequest {

	@NotNull
	@NotBlank
	private String conditionStr;
	private int limit;
	private int offset;
	private List<String> orderBys;
	private List<String> columeSelect;
}
