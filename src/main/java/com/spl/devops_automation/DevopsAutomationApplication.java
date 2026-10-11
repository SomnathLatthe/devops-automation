package com.spl.devops_automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class DevopsAutomationApplication {

	@GetMapping
	public String message(){
		return "Welcome to Devops Journey...";
	}
	public static void main(String[] args) {
		SpringApplication.run(DevopsAutomationApplication.class, args);
	}

}
