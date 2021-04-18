package com.buptse.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//@LoadBalancerClient(name = "UTIL-SERVICE-PROVIDER",configuration = RoundRobinLoadBalancer.class)
//  自定义轮询策略和服务ID    默认轮询
public class ConfigBean {
    //将这个对象放入ioc容器
    @Bean
    @LoadBalanced  //使用这个注解给restTemplate赋予了负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
