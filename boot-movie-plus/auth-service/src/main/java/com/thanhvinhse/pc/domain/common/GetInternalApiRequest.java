package com.thanhvinhse.pc.domain.common;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetInternalApiRequest {

	@NotNull
	private String conditionStr;
	private int limit;
	private int offset;
	private String orderByStr;
}
