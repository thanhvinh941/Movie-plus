package com.movieplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MoviePlusSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviePlusSystemApplication.class, args);
	}

}
