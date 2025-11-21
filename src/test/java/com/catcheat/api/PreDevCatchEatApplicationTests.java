package com.catcheat.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootTest
class PreDevCatchEatApplicationTests {

	@Test
	void contextLoads() {
	}

    @EnableJpaAuditing
    @SpringBootApplication
    public class PreDevCatchEatApplication {
        public static void main(String[] args) {
            SpringApplication.run(PreDevCatchEatApplication.class, args);
        }
    }
}
