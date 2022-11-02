package com.gcit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.sql.SQLException;


@SpringBootApplication
@EntityScan(basePackages = "com.gcit.springbootreact.model")
@EnableJpaRepositories(basePackages = {"com.gcit.springbootreact.repository"})
@ComponentScan(basePackages = {"com.gcit.springbootreact.repository", "com.gcit.springbootreact.controller"})
public class SpringBootReactApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(SpringBootReactApplication.class, args);
    }
}
