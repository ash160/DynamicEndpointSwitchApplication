package com.DynamicEndpointSwitch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class DynamicEndpointSwitchApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicEndpointSwitchApplication.class, args);
	}

}
