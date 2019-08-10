package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot05Application {

	public static void main(String[] args) {
		
		SpringApplication application = new SpringApplication(SpringBoot05Application.class);
		
		application.setWebApplicationType(WebApplicationType.NONE);
		
		application.run(args);
		
		//SpringApplication.run(SpringBoot05Application.class, args);
	}

}
