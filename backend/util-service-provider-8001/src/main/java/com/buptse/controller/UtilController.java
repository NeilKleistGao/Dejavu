package com.buptse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UtilController
 * @Description TODO
 * @Author ASJA
 * @Date 2021/5/7 18:00
 * @Version 1.0
 */

@RestController
public class UtilController {
    //获取一些配置的信息，得到具体的微服务
    @Autowired
    private DiscoveryClient client;


    //注册进来的微服务，获取一些消息
    @GetMapping("/discovery")
    public Object discovery() {
        //获得微服务列表的清单
        List<String> services = client.getServices();
        System.out.println("discovery=>services:" + services);
        //得到一个具体的微服务信息
        List<ServiceInstance> instances = client.getInstances("UTIL-SERVICE-PROVIDER");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost() + "\t" +
                            instance.getPort() + "\t" +
                            instance.getUri() + "\t" +
                            instance.getServiceId() + "\t"
            );
        }
        return this.client;
    }
}