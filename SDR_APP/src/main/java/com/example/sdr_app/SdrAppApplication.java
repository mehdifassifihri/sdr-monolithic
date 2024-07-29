package com.example.sdr_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SdrAppApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SdrAppApplication.class, args);
    }

}
