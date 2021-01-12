package com.fhict.dao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.fhict.*"})
@ComponentScan(basePackages = {"com.fhict.*"})
@EntityScan(basePackages = {"com.fhict.*"})
@EnableJpaRepositories(basePackages = {"com.fhict.*"})
public class RepositoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(RepositoryApplication.class, args);
    }
}
