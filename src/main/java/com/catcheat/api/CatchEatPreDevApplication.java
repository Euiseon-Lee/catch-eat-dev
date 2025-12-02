package com.catcheat.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CatchEatPreDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatchEatPreDevApplication.class, args);
	}
}
