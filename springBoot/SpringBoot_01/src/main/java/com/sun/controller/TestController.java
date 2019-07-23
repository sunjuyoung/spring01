package com.sun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	
	public TestController() {
		
		System.out.println("testController 생성");
	}

	@GetMapping("/sun")
	public String test(String name) {
		
		
		return "sun" + name;
		
	}
	
	
}
