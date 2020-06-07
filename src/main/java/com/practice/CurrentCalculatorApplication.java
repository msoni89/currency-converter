package com.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;

@SpringBootApplication
public class CurrentCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrentCalculatorApplication.class, args);
	}

	@Bean
	public Gson gson() {
		return new Gson();
	}
}
