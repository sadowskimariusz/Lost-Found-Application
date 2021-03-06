package com.lostandfoundapp.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.sql.DataSourceDefinition;

@ComponentScan("com.lostandfoundapp")
@EnableScheduling
@SpringBootApplication
public class SpringApp {

    public static void main(String[] args) {
        SpringApplication.run(  SpringApp.class, args);
    }
}
