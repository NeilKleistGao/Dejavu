package com.buptse.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptse.pojo.Car;

public class CarWrapperUtil {
  public static QueryWrapper<Car> CarQueryWrapper(String region,
      String model,
      Integer minGuidePrice,
      Integer maxGuidePrice,
      String manufacturer,
      Integer server,
      Integer mileage,
      Integer displacement,
      Integer minPrice,
      Integer maxPrice,
      String body,
      String fuel,
      String gear,
      String orderBy,
      Boolean order,
      String key) {
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
    return queryWrapper;
  }
}
