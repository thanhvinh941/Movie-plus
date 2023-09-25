package src.main.java.com.movieplus.controller;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BookingBachController {

	@Scheduled(cron = "@hourly")
	public void perform() throws Exception {
		System.out.println("====>> print method()..." + (new Date()).toString());
	}
}
