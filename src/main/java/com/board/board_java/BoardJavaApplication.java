package com.board.board_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class BoardJavaApplication {
    public static void main(String[] args) {

        SpringApplication.run(BoardJavaApplication.class, args);
    }
}
