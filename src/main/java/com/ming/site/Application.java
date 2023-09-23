package com.ming.site;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        log.info("Hello 李明明!");

        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        return args -> {
            log.info("Let's inspect the beans provided by Spring Boot:");


//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//
//            for(String beanName: beanNames){
//                log.info(beanName);
//            }
        };
    }
}