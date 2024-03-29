package com.ailu.feeds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("generator.mapper")
public class FeedsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedsApplication.class, args);
    }

}
