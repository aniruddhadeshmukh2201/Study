package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(PracticeApplication.class, args);

		Sample s = context.getBean(Sample.class);

		s.increment();
		System.out.println("count : " + s.getCount());

		Sample d = context.getBean(Sample.class);
		d.increment();
		System.out.println("count in d : " + d.getCount());
		System.out.println("count : " + s.getCount());
	}

}
