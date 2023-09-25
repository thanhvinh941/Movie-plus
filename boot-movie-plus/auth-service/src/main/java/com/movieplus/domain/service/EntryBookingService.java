package com.movieplus.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.movieplus.config.AuthenticationFacade;
import com.movieplus.config.UserSession;
import com.movieplus.controller.external.EntryBookingController.EntryBookingRequest;
import com.movieplus.domain.entity.BookingInfo;
import com.movieplus.domain.entity.ReseverSeat;
import com.movieplus.domain.payload.response.EntryBookingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntryBookingService {

	private final AuthenticationFacade authenticationFacade;

	public void execute(EntryBookingRequest request, EntryBookingResponse response) {

		UserSession userSession = authenticationFacade.getAuthentication();
		
		// TODO check isAvailable Seat
		validateShowTime(request);

		doRegistBookingAndResever(request, userSession);

		// TODO Auto-generated method stub

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void doRegistBookingAndResever(EntryBookingRequest request, UserSession userSession) {
		try {			
			// TODO build entity BookingInfo
			BookingInfo bookingInfo = new BookingInfo();
			bookingInfo.setBookingKbn(0);
			bookingInfo.setShowTimeId(request.getShowTimeId());
			bookingInfo.setUserId(userSession.getId());
			bookingInfo.setRegistTime(LocalDateTime.now());
			bookingInfo.setUpdateTime(LocalDateTime.now());
			// TODO do Entry bookingInfo
			
			validateReseverSeat(request);
			
			// TODO build entity ReseverSeat
			List<ReseverSeat> eReseverSeats = request.getSeatIds().stream().map(ers -> {
				ReseverSeat rs = new ReseverSeat();
				rs.setBookingId(bookingInfo.getId());
				rs.setSeatId(ers);
				return rs;
			}).toList();
			// TODO do Entry eReseverSeats
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void validateReseverSeat(EntryBookingRequest request) {
		// TODO Auto-generated method stub
		
	}

	private void validateShowTime(EntryBookingRequest request) {
		// TODO Auto-generated method stub

	}

}
