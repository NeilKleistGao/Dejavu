package com.buptse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptse.common.util.CityAndCodeUtil;
import com.buptse.common.util.bodyAndCodeUtil;
import com.buptse.common.util.fuelAndCodeUtil;
import com.buptse.common.util.gearboxAndCodeUtil;
import com.buptse.common.util.repairedAndCodeUtil;
import com.buptse.dto.CarDto;
import com.buptse.pojo.Car;
import com.buptse.service.ICarService;
import com.buptse.service.IUserService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
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
    @RequiresRoles({"manage"})
    @GetMapping("/car")
    public CarDto getCarById(@RequestParam Integer id){
        return carService.getCarDto(carService.getById(id));
    }
    @GetMapping("/car/pn")
    public Map getCarLength(){
        QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        Integer count = carService.count();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("page-number",count);
        return map;
    }
    @PostMapping("/car/new")
    public Map createCar(
        @RequestParam(value = "model")String model,
        @RequestParam(value = "guild_price")Float guild_price,
        @RequestParam(value = "manufactue")String manufactue,
        @RequestParam(value = "server_life")Float server_life,
        @RequestParam(value = "mileage")Float mileage,
        @RequestParam(value = "displacement")Float displacement,
        @RequestParam(value = "region")String region,
        @RequestParam(value = "price")Float price,
        @RequestParam(value = "body_type")String body_type,
        @RequestParam(value = "fuel_type")String fuel_type,
        @RequestParam(value = "gearbox")String gearbox,
        @RequestParam(value = "power")Float power,
        @RequestParam(value = "not_repaired_damage")String not_repaird_damage,
        @RequestParam(value = "create_data")Date create_data){
        Car car = new Car();
        car.setModel_name(model);
        if(guild_price!=null) car.setGuide_price(BigDecimal.valueOf(guild_price));
        car.setManufacturer(manufactue);
        if(server_life!=null)car.setService_life(BigDecimal.valueOf(server_life));
        if(mileage!=null)car.setMileage(BigDecimal.valueOf(mileage));
        if(displacement!=null)car.setDisplacement(BigDecimal.valueOf(displacement));
        if(region!=null)car.setRegion_code(Integer.valueOf(CityAndCodeUtil.getCodeByCity(region)));
        if(price!=null)car.setPrice(BigDecimal.valueOf(price));
        if(body_type!=null)car.setBody_type(bodyAndCodeUtil.getCodeByBody(body_type));
        if(fuel_type!=null)car.setFuel_type(fuelAndCodeUtil.getCodeByFuel(fuel_type));
        if(gearbox!=null)car.setGear_box(gearboxAndCodeUtil.getCodeByGear(gearbox));
        if(power!=null)car.setPrice(BigDecimal.valueOf(power));
        if(not_repaird_damage!=null)car.setNot_repaired_damage(repairedAndCodeUtil.getCodeByRequired(not_repaird_damage));
        if(create_data!=null)car.setCreate_date(create_data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        else car.setCreate_date(LocalDateTime.now());
        int id = carService.insertCarAndGetId(car);
        Map res = new HashMap();
        res.put("car_id",id);
        return res;
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
        @RequestParam(value = "order",required = false)Boolean order,
        @RequestParam(value = "key",required = false)String key){
        final QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        if(region!=null){
            String cityCode = CityAndCodeUtil.getCodeByCity(region);
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
        if(key!=null){
            queryWrapper.like("model_name",key);
        }
        final List<CarDto> carList = carService.getCarDtoList(carService.list(queryWrapper));
        return carList;
    }

}
