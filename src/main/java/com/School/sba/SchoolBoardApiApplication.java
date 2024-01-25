package com.School.sba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.School.sba.controller.SchoolController;

@SpringBootApplication
public class SchoolBoardApiApplication {

	private static ConfigurableApplicationContext run;

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SchoolBoardApiApplication.class, args);

		

	}

}
