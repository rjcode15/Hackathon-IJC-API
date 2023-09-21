package com.pa.vote.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class LoginController {

	
	@GetMapping("/")
	public String sayHello() {
		return "Hello World";
	}
}
