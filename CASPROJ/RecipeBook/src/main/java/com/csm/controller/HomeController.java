package com.csm.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class HomeController {
	
	@GetMapping
	public String login() {
		return "ITs too late and im here to stay!";
	}

}
