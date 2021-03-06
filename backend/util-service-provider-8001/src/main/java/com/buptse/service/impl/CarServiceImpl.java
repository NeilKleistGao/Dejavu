package com.buptse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptse.common.util.CarAndcarDtoUtil;
import com.buptse.dto.CarDto;
import com.buptse.mapper.CarMapper;
import com.buptse.pojo.Car;
import com.buptse.pojo.Carimg;
import com.buptse.service.ICarService;
import com.buptse.service.ICarimgService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private ICarimgService carimgService;

  @Override
  public List<CarDto> getCarDtoList(List<Car> carList) {
    List<CarDto> carDtoList = new ArrayList<>();
    for (Car car : carList) {
      CarDto carDto = CarAndcarDtoUtil.car2carDto(car);
      QueryWrapper<Carimg> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("car_id",carDto.getCar_id());
      List<Carimg> list = carimgService.list(queryWrapper);
      final List<String> imgs = list.stream().map(Carimg::getImg).collect(Collectors.toList());
      carDto.setImgs(imgs);
      carDtoList.add(carDto);
    }
    return carDtoList;
  }

  @Override
  public CarDto getCarDto(Car car) {
    return CarAndcarDtoUtil.car2carDto(car);
  }

  @Override
  public int insertCarAndGetId(Car car) {
    getBaseMapper().insertCar(car);
    return car.getCar_id();
  }


}
