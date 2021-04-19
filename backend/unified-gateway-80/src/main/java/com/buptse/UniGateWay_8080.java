package com.buptse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UniGateWay_8080 {
    public static void main(String[] args) {
        SpringApplication.run(UniGateWay_8080.class,args);
    }
}
