package com.movieplus.config;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdminSession extends UserSession {

	private List<String> organizationIds;
}
