package com.evan.xh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories(basePackages = "com.evan.xh.dao")
@SpringBootApplication
@EnableSwagger2
public class XhApplication {

    public static void main(String[] args) {
        SpringApplication.run(XhApplication.class, args);
    }

}
