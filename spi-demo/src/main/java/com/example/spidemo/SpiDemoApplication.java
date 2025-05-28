package com.example.spidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpiDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpiDemoApplication.class, args);
    }
}