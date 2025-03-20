package com.example.carmonasportoutlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.carmonasportoutlet.Controller", "com.example.carmonasportoutlet.Services"})

public class CarmonasportoutletApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarmonasportoutletApplication.class, args);
    }

}
