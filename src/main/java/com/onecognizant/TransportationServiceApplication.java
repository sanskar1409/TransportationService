package com.onecognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.onecognizant")
public class TransportationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportationServiceApplication.class, args);
	}

}
