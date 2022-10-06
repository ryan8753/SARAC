package com.sarac.sarac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.cache.annotation.EnableCaching;

@EnableJpaAuditing
@SpringBootApplication
@EnableCaching
public class SaracApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaracApplication.class, args);
    }

}
