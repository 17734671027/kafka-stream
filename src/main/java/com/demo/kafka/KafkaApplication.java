package com.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
public class KafkaApplication {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());



    public static void main(String[] args) {

        SpringApplication.run(KafkaApplication.class, args);
    }

}

