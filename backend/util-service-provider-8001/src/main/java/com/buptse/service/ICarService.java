package com.buptse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buptse.dto.CarDto;
import com.buptse.pojo.Car;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ASJA
 * @since 2021-04-23
 */
public interface ICarService extends IService<Car> {
  List<CarDto> getCarDtoList(List<Car> carList);
  CarDto getCarDto(Car car);
}
