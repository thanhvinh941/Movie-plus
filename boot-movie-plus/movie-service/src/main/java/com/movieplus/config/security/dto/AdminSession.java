package com.movieplus.config.security.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdminSession extends UserSession {

	private List<String> permission;
}
