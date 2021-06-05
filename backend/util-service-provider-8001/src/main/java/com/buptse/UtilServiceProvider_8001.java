package com.buptse;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value = "com.buptse.mapper")
public class UtilServiceProvider_8001 {
    public static void main(String[] args) {
        SpringApplication.run(UtilServiceProvider_8001.class,args);
    }
}
