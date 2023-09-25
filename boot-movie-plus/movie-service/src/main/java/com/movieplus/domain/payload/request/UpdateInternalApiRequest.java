package com.movieplus.domain.payload.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class UpdateInternalApiRequest<ID> {
	@NotNull
	@NotEmpty
	private Map<String, Object> params;
	
	private ID id;
}
