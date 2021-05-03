package com.xbank.demo.graph.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GraphController {
	
	@GetMapping("/test")
	public String test() {
		return "Success";
	}
	
	
}
