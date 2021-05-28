package com.buptse.controller;

import com.buptse.UtilServiceProvider_8001;
import com.buptse.mapper.CarMapper;
import com.buptse.pojo.Car;
import com.buptse.service.ICarService;
import com.buptse.service.impl.CarServiceImpl;
import com.netflix.discovery.converters.Auto;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarControllerTest {
  @Autowired
  ICarService carService;
  @Test
  public void Test(){
    Car car = new Car();
    carService.insertCarAndGetId(car);
    System.out.println(car.getCar_id());
  }
}
