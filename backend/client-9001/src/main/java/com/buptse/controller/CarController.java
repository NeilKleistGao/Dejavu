package com.buptse.controller;

import com.buptse.pojo.Car;
import com.buptse.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CarController
 * @Description TODO
 * @Author ASJA
 * @Date 2021/5/7 17:58
 * @Version 1.0
 */

@RestController
public class CarController {

    @Autowired
    CarService service;

    @GetMapping("/car")
    public Car getCarById(@RequestParam Integer id) {
        return service.getCarById(id);
    }
}
