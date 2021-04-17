package com.buptse.controller;


import com.buptse.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class demoController {

    @Autowired
    private RestTemplate restTemplate;

    private static final  String REST_URL_PREFIX = "http://localhost:8001";

    @RequestMapping("/user/get/{uid}")
    public User queryById(@PathVariable("uid") Integer uid) {
        return restTemplate.getForObject(REST_URL_PREFIX+"/user/get/"+uid,User.class);
    }


}
