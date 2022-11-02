package com.javatechie.crud.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.javatechie.crud.example.entity")
@EnableJpaRepositories(basePackages = {"com.javatechie.crud.example.repository"})
@ComponentScan(basePackages = {"com.javatechie.crud.example.repository", "com.javatechie.crud.example.service", "com.javatechie.crud.example.controller","com.javatechie.crud.example.entity"})
public class SpringBootCrudExample2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudExample2Application.class, args);
	}

}
