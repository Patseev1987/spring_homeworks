package ru.bogdan.homework4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Homework4Application {

	public static void main(String[] args) {
		SpringApplication.run(Homework4Application.class, args);
	}

}
