package com.wheel.gateway.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by rolyer on 17/11/16.
 */
@SpringBootApplication(scanBasePackages = {"com.wheel.gateway.web"})
public class WheelApplication {
    public static void main(String[] args) {
        SpringApplication.run(WheelApplication.class, args);
    }
}
