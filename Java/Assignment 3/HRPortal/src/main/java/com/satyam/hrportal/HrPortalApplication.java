package com.satyam.hrportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
public class HrPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrPortalApplication.class, args);
    }

}
