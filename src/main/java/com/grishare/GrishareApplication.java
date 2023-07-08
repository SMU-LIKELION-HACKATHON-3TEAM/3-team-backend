package com.grishare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.ComponentScan;

@EnableScheduling
@SpringBootApplication
public class GrishareApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrishareApplication.class, args);
	}

}
