package com.tome.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class TomeBackendAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TomeBackendAppApplication.class, args);
	}

}
