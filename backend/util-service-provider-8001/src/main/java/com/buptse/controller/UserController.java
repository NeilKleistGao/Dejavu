package com.buptse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptse.common.util.*;
import com.buptse.dto.LoginDto;
import com.buptse.mapper.UserMapper;
import com.buptse.pojo.Car;
import com.buptse.pojo.User;
import com.buptse.pojo.UserToken;
import com.buptse.service.ICarService;
import com.buptse.service.IShiroService;
import com.buptse.service.IUserService;
import java.nio.charset.MalformedInputException;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private IUserService service;
    @Autowired
    private ICarService carService;
    @Autowired
    IShiroService shiroService;
    @GetMapping("/user/get/{uid}")
    public User getUSerById(@PathVariable("uid") Integer uid) {
        User user = service.getById(uid);
        return user;
    }
    @RequiresRoles({"manage"})
    @GetMapping("/car/{id}")
    public Car getCar(@PathVariable("id") Integer carId){
        Car car = carService.getById(carId);
        return car;
    }

    @PostMapping("/user/login")
    public Map<String,Object> loginUser(
        @RequestParam("phoneNumber")String phoneNumber,
        @RequestParam("password")String password){
        Map<String,Object> result = new HashMap<>();
        User user = shiroService.findByUserPhone(phoneNumber);
        if(user == null || !user.getPassword().equals(password)){
            result.put("status",400);
            result.put("info","密码错误");
        }else{
            result = shiroService.createToken(user.getUid());
            result.put("uid",user.getUid());
            result.put("status",200);
            result.put("info","登录成功");
        }
        return result;
    }



}
