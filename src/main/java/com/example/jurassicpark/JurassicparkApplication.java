package com.example.jurassicpark;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JurassicparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(JurassicparkApplication.class, args);
		System.out.println("Jurassic Park");

	}

}
