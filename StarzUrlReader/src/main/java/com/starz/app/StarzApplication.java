package com.starz.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication

@ComponentScan({"com.starz.controller", "com.starz.service","com.starz.util"})
public class StarzApplication {



	public static void main(String[] args) {
		
		SpringApplication.run(StarzApplication.class, args);

	}

}
