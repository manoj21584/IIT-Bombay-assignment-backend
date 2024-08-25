package com.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class CourseApplication {

	public static void main(String[] args) {

		SpringApplication.run(CourseApplication.class, args);
	}

}
