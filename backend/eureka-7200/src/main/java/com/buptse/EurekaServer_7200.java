package com.buptse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaServer_7200
 * @Description TODO
 * @Author ASJA
 * @Date 2021/4/17 14:32
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer_7200 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_7200.class,args);
    }
}
