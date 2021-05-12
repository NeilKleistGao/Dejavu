package com.buptse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptse.common.util.CityAndCodeUtil;
import com.buptse.common.util.bodyAndCodeUtil;
import com.buptse.common.util.fuelAndCodeUtil;
import com.buptse.common.util.gearboxAndCodeUtil;
import com.buptse.dto.CarDto;
import com.buptse.pojo.Car;
import com.buptse.service.ICarService;
import com.buptse.service.IUserService;
import java.util.List;
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
    private ICarService carService;

    @GetMapping("/car")
    public CarDto getCarById(@RequestParam Integer id){
        return carService.getCarDto(carService.getById(id));
    }
    @GetMapping("/car/list")
    public List<CarDto> searchCarList(
        @RequestParam(value = "region",required = false) String region,
        @RequestParam(value = "model",required = false)String model,
        @RequestParam(value = "min-guide-price",required = false) Integer minGuidePrice,
        @RequestParam(value = "max-guide-price",required = false) Integer maxGuidePrice,
        @RequestParam(value = "manufacturer",required = false) String manufacturer,
        @RequestParam(value = "server",required = false) Integer server,
        @RequestParam(value = "mileage",required = false) Integer mileage,
        @RequestParam(value = "displacement",required = false)Integer displacement,
        @RequestParam(value = "min-price",required = false)Integer minPrice,
        @RequestParam(value = "max-price",required = false)Integer maxPrice,
        @RequestParam(value = "body",required = false)String body,
        @RequestParam(value = "fuel",required = false)String fuel,
        @RequestParam(value = "gear",required = false)String gear,
        @RequestParam(value = "order-by",required = false)String orderBy,
        @RequestParam(value = "order",required = false)Boolean order){
        final QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        if(region!=null){
            String cityCode = CityAndCodeUtil.getCodeByCity(region);
            System.out.println(cityCode);
            queryWrapper.eq("region_code",cityCode);
        }
        if(model!=null)queryWrapper.eq("model",model);
        if(minGuidePrice!=null)queryWrapper.ge("guide_price",minGuidePrice);
        if(maxGuidePrice!=null)queryWrapper.le("guide_price",maxGuidePrice);
        if(manufacturer!=null)queryWrapper.eq("manufacturer",manufacturer);
        if(server!=null)queryWrapper.eq("service_life",server);
        if(mileage!=null)queryWrapper.le("mileage",mileage);
        if(displacement!=null)queryWrapper.eq("dispalcement",displacement);
        if(minPrice!=null)queryWrapper.ge("price", minPrice);
        if(maxPrice!=null)queryWrapper.le("price",maxPrice);
        if(body!=null){
            // 获取枚举类的编号
            final Integer codeByBody = bodyAndCodeUtil.getCodeByBody(body);
            queryWrapper.eq("body_type",codeByBody);
        }
        if(fuel!=null) {
            // 获取映射类编号
            final Integer codeByFuel = fuelAndCodeUtil.getCodeByFuel(fuel);
            queryWrapper.eq("fuel_type",codeByFuel);
        }
        if(gear!=null){
            // 获取映射编号
            final Integer codeByGear = gearboxAndCodeUtil.getCodeByGear(gear);
            queryWrapper.eq("gearbox",codeByGear);
        }
        if(orderBy!=null){
            // 根据那个进行order
            if(order){
                queryWrapper.orderByAsc(orderBy);
            }else{
                queryWrapper.orderByDesc(orderBy);
            }
        }
        final List<CarDto> carList = carService.getCarDtoList(carService.list(queryWrapper));
        return carList;
    }

}
