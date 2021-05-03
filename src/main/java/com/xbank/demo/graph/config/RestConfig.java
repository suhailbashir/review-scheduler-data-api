package com.xbank.demo.graph.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	
	@Bean
	public RestTemplate restTeamplete() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	
}
