package com.movieplus.controller.external;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.config.AuthenticationFacade;
import com.movieplus.domain.common.GeneratorCommonUtil;
import com.movieplus.domain.payload.response.EntryBookingResponse;
import com.movieplus.domain.service.EntryBookingService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class EntryBookingController {

	private final EntryBookingService service;
	
	@Data
	public static class EntryBookingRequest{
		private List<String> seatIds;
		private String showTimeId;
	}
	
	@PostMapping("/entryBooking")
	@ResponseBody
	public String doEntryBooking(@RequestBody EntryBookingRequest request, Authentication authentication) {
		EntryBookingResponse response = new EntryBookingResponse();
		try {
			service.execute(request, response);
			
			return GeneratorCommonUtil.getResponseBodySuccess(response);
		} catch (Exception e) {
			return GeneratorCommonUtil.getResponseBodyError(List.of(e.getMessage()));
		}
	}
}
