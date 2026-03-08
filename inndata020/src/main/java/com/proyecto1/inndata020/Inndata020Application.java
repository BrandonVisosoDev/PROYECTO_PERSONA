package com.proyecto1.inndata020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Inndata020Application {

	public static void main(String[] args) {
		SpringApplication.run(Inndata020Application.class, args);
	}
}