package com.buptse.controller;

import com.buptse.pojo.User;
import com.buptse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author ASJA
 * @Date 2021/5/7 17:58
 * @Version 1.0
 */

@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/user/get/{uid}")
    public User queryById(@PathVariable("uid") Integer uid) {
        return service.getUserById(uid);
    }
}
