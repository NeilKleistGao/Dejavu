package com.buptse.service;

import com.buptse.pojo.Car;
import com.buptse.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CarService
 * @Description TODO
 * @Author ASJA
 * @Date 2021/5/7 18:26
 * @Version 1.0
 */
@Service
public class CarService {

    @Autowired
    private RestTemplate restTemplate;

    //private static final  String REST_URL_PREFIX = "http://localhost:8001";

    // for eureka cluster
    private static final  String REST_URL_PREFIX = "http://UTIL-SERVICE-PROVIDER";

    public Car getCarById(Integer id) {
        return restTemplate.getForObject(REST_URL_PREFIX+"/car?id="+id,Car.class);
    }



}