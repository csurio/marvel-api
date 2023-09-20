package com.siman.assestment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/status")
public class HomeController {
	
	@GetMapping	
	public String getStatus() {return "Ok";}
}
