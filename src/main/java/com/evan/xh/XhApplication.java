package com.evan.xh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.evan.xh.dao")
@SpringBootApplication
public class XhApplication {

    public static void main(String[] args) {
        SpringApplication.run(XhApplication.class, args);
    }

}
