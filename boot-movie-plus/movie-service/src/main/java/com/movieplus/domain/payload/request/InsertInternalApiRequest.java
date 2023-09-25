package com.movieplus.domain.payload.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InsertInternalApiRequest<T> {

	@NotNull
	@NotEmpty
	private List<T> records;
}
