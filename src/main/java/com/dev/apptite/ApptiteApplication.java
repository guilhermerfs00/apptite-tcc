package com.dev.apptite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApptiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApptiteApplication.class, args);
    }
}