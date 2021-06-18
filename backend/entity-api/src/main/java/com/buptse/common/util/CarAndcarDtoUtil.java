package com.buptse.common.util;

import com.buptse.dto.CarDto;
import com.buptse.pojo.Car;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class CarAndcarDtoUtil {

  public  static CarDto car2carDto(Car car){
    CarDto carDto = new CarDto();
    carDto.setCar_id(car.getCar_id());
    carDto.setBody_type(bodyAndCodeUtil.getBodyByCode(car.getBody_type()));
    carDto.setCreate_date(car.getCreate_date());
    carDto.setDisplacement(car.getDisplacement());
    carDto.setFuel_type(fuelAndCodeUtil.getFuelByCode(car.getFuel_type()));
    carDto.setGear_box(gearboxAndCodeUtil.getGearByCode(car.getGear_box()));
    carDto.setGuide_price(car.getGuide_price());
    carDto.setManufacturer(car.getManufacturer());
    carDto.setMileage(car.getMileage());
    carDto.setNot_repaired_damage(repairedAndCodeUtil.getRequiredByCode(car.getNot_repaired_damage()));
    carDto.setModel_name(car.getModel_name());
    carDto.setCar_id(car.getCar_id());
    carDto.setPower(car.getPower());
    carDto.setPrice(car.getPrice());
    carDto.setService_life(car.getService_life());
    carDto.setUid(car.getUid());
    carDto.setState(car.getState());
    carDto.setRegion(CityAndCodeUtil.getCityByCode(car.getRegion_code()));
    return carDto;
  }
}
