package com.buptse.controller;


import com.buptse.pojo.User;
import com.buptse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user/get/{uid}")
    public User queryById(@PathVariable("uid") Integer uid) {
        return service.queryById(uid);
    }

}
