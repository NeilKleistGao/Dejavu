package com.buptse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptse.common.util.CarAndcarDtoUtil;
import com.buptse.dto.CarDto;
import com.buptse.mapper.CarMapper;
import com.buptse.pojo.Car;
import com.buptse.service.ICarService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ASJA
 * @since 2021-04-23
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

  @Override
  public List<CarDto> getCarDtoList(List<Car> carList) {
    List<CarDto> carDtoList = new ArrayList<>();
    for (Car car : carList) {
      CarDto carDto = CarAndcarDtoUtil.car2carDto(car);
      carDtoList.add(carDto);
    }
    return carDtoList;
  }

  @Override
  public CarDto getCarDto(Car car) {
    return CarAndcarDtoUtil.car2carDto(car);
  }
}
