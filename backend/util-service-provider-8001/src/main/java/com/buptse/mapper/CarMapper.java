package com.buptse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buptse.pojo.Car;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ASJA
 * @since 2021-04-23
 */
public interface CarMapper extends BaseMapper<Car> {
  @Insert("insert into car (model_name,guide_price,manufacturer,"
      + "service_life,mileage,displacement,region_code,uid,price,state,"
      + "body_type,fuel_type,gear_box,power,not_repaired_damage,create_date)"
      + "values(#{model_name},#{guide_price},#{manufacturer},#{service_life},"
      + "#{mileage},#{displacement},#{region_code},#{uid},#{price},#{state},"
      + "#{body_type},#{fuel_type},#{gear_box},#{power},#{not_repaired_damage},#{create_date})")
  @Options(useGeneratedKeys = true,keyProperty = "car_id",keyColumn = "car_id")
  int insertCar(Car car);
}
