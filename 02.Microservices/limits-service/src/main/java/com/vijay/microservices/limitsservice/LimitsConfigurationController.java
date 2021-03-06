package com.vijay.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vijay.microservices.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration retrieveValueFromConfig() {
		return new LimitConfiguration(configuration.getMinimum(),
				configuration.getMaximum());
	}
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallbackRetrieveConfig")
	public LimitConfiguration retrieveConfig() {
		throw new RuntimeException("Not Available");
	}
	
	public LimitConfiguration fallbackRetrieveConfig() {
		return new LimitConfiguration(9,999);
	}

}
