package com.se.thanhvinh.simple.controller.external;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/external")
public class HelloController {

	@GetMapping("/hello")
	public @ResponseBody String hello() {
		return "hello";
	}
}
