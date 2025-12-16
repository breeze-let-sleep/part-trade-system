package com.hyltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PartTradeSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartTradeSystemApplication.class, args);
    }

}