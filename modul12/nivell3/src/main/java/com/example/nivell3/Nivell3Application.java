package com.example.nivell3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class }) // Per l'error de no tenir una DB configurada
@SpringBootApplication
public class Nivell3Application {

	public static void main(String[] args) {
		SpringApplication.run(Nivell3Application.class, args);
	}

}
