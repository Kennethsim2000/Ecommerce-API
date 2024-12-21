package com.example.Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.Demo.exception.GlobalExceptionHandler;

@SpringBootApplication
@Import(GlobalExceptionHandler.class)
public class MeekPointsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeekPointsApplication.class, args);
	}

}
