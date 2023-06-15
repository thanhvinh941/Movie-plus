package com.se.thanhvinh.simple.domain.payload.request;

import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class ExternalApiRequest<T> {

	@Valid
	private T input;
}
