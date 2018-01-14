package com.lostandfoundapp.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.lostandfoundapp")
@EnableScheduling
@SpringBootApplication
public class SpringApp {

    public static void main(String[] args) {
        SpringApplication.run(  SpringApp.class, args);
    }
}
