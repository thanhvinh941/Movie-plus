package com.movieplus.controller.external;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.payload.response.RetrieveSiteInfoResponse;
import com.movieplus.domain.service.RetrieveSiteInfoService;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class RetrieveSiteInfoController {

	@Getter
	@Setter
	public static class RetrieveSiteInfoRequest{
		@NotNull
		private String siteId;
	}
	
	private final RetrieveSiteInfoService service;
	
	@PostMapping("/retrieveSiteInfo")
	@ResponseBody
	public String doRetrieveSiteInfo(@RequestBody RetrieveSiteInfoRequest request) {
		try {
			RetrieveSiteInfoResponse response = new RetrieveSiteInfoResponse();
			service.execute(request, response);
			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
