package com.example.allknuauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AllKnuAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllKnuAuthApplication.class, args);
	}

}
