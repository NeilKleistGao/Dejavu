package com.buptse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptse.common.util.CityAndCodeUtil;
import com.buptse.common.util.bodyAndCodeUtil;
import com.buptse.common.util.fuelAndCodeUtil;
import com.buptse.common.util.gearboxAndCodeUtil;
import com.buptse.common.util.repairedAndCodeUtil;
import com.buptse.dto.CarDto;
import com.buptse.pojo.Car;
import com.buptse.pojo.Carimg;
import com.buptse.service.ICarService;
import com.buptse.service.ICarimgService;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private ICarimgService carimgService;
    @Autowired
    private ICarService carService;
    @GetMapping("/car")
    public CarDto getCarById(@RequestParam Integer id){
        CarDto carDto = carService.getCarDto(carService.getById(id));
        QueryWrapper<Carimg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id",id);
        List<Carimg> list = carimgService.list(queryWrapper);
        List<String> imglist = new LinkedList<>();
        for (Carimg carimg : list) {
            imglist.add(carimg.getImg());
        }
        carDto.setImgs(imglist);
        return carDto;
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
            @RequestBody CarDto carDto){
        Car car = Car.builder()
                .car_id(carDto.getCar_id())
                .body_type(bodyAndCodeUtil.getCodeByBody(carDto.getBody_type()))
                .create_date(LocalDateTime.now())
                .displacement(carDto.getDisplacement())
                .fuel_type(fuelAndCodeUtil.getCodeByFuel(carDto.getFuel_type()))
                .gear_box(bodyAndCodeUtil.getCodeByBody(carDto.getGear_box()))
                .guide_price(carDto.getGuide_price())
                .manufacturer(carDto.getManufacturer())
                .mileage(carDto.getMileage())
                .model_name(carDto.getModel_name())
                .not_repaired_damage(repairedAndCodeUtil.getCodeByRequired(carDto.getNot_repaired_damage()))
                .power(carDto.getPower())
                .price(carDto.getPrice())
                .region_code(Integer.parseInt(CityAndCodeUtil.getCodeByCity(carDto.getRegion())))
                .service_life(carDto.getService_life())
                .state(carDto.getState())
                .uid(carDto.getUid())
                .build();
        List<String> imgs = carDto.getImgs();
        for (String img : imgs) {
            carimgService.save(new Carimg(carDto.getCar_id(),img));
        }
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
    @GetMapping("/car/{id}")
    /**
     * @Description: 根据车辆ID获取车的信息
     * @Param: [carId]
     * @return: com.buptse.pojo.Car
     * @Author: gerayking
     * @Date: 2021/6/5-14:40
     */
    public Car getCar(@PathVariable("id") Integer carId) {
        Car car = carService.getById(carId);
        return car;
    }

}
