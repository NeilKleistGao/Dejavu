package com.buptse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.buptse.dao")
public class UtilServiceProvider {
    public static void main(String[] args) {
        SpringApplication.run(UtilServiceProvider.class,args);
    }
}
