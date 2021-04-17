package com.buptse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
// @Configuration --- spring applicationContext.xml
public class ConfigBean {

    //配置负载均衡实现RestTemplate
    @Bean
    //@LoadBalanced //Ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}