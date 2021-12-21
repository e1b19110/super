package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SuperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperApplication.class, args);
	}

}
