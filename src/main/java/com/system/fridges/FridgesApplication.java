package com.system.fridges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.system.fridges")
public class FridgesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FridgesApplication.class, args);
	}

}
