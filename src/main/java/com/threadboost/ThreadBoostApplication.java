package com.threadboost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ThreadBoostApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreadBoostApplication.class, args);
        System.out.println("ThreadBoost Application Started Successfully!");
    }
}