package com.buptse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptse.common.util.*;
import com.buptse.mapper.UserMapper;
import com.buptse.pojo.Car;
import com.buptse.pojo.User;
import com.buptse.service.ICarService;
import com.buptse.service.IUserService;
import java.nio.charset.MalformedInputException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private IUserService service;
    @Autowired
    private ICarService carService;
    @GetMapping("/user/get/{uid}")
    public User getUSerById(@PathVariable("uid") Integer uid) {
        User user = service.getById(uid);
        return service.getById(uid);
    }
    @GetMapping("/car/{id}")
    public Car getCar(@PathVariable("id") Integer carId){
        Car car = carService.getById(carId);
        return car;
    }



}