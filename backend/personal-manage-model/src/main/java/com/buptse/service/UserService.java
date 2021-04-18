package com.buptse.service;

import com.buptse.pojo.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;


@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    //private static final  String REST_URL_PREFIX = "http://localhost:8001";

    // for ribbon
    private static final  String REST_URL_PREFIX = "http://UTIL-SERVICE-PROVIDER";

    public User queryById(Integer uid) {
        return restTemplate.getForObject(REST_URL_PREFIX+"/user/get/"+uid,User.class);
    }


}
