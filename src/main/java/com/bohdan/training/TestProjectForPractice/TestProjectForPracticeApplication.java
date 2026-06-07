package com.bohdan.training.TestProjectForPractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TestProjectForPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestProjectForPracticeApplication.class, args);
	}
}
