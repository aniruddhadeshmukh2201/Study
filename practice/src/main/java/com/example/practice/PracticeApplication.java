package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(PracticeApplication.class, args);

		Sample s = context.getBean("SingletonService", Sample.class);
		Sample p = context.getBean("PrototypeService", Sample.class);


	}

}
