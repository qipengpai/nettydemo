package com.qpp.nettydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.qpp.nettydemo.dao")
@SpringBootApplication
public class NettydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettydemoApplication.class, args);
    }
}
