package com.fhict.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = {"com.fhict.*"})
@ComponentScan(basePackages = {"com.fhict.*"})
@EntityScan(basePackages = {"com.fhict.*"})
@EnableJpaRepositories(basePackages = {"com.fhict.*"})
@EnableWebSecurity
public class PinterestApplication {
    public static void main(String[] args) {
        SpringApplication.run(PinterestApplication.class, args);
    }
}
