package com.buptse.controller;


import com.buptse.pojo.User;
import com.buptse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class demoController {

    @Autowired
    UserService service;

    @GetMapping("/user/get/{uid}")
    public User queryById(@PathVariable("uid") Integer uid) {
        return service.queryById(uid);
    }


}
