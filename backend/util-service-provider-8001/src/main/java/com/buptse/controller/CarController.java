package com.buptse.controller;

import com.buptse.pojo.Car;
import com.buptse.service.ICarService;
import com.buptse.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CarController
 * @Description TODO
 * @Author ASJA
 * @Date 2021/5/7 18:01
 * @Version 1.0
 */

@RestController
public class CarController {

    @Autowired
    private ICarService service;

    @GetMapping("/car")
    public Car getCarById(@RequestParam String id){
        return service.getById(id);
    }

}
