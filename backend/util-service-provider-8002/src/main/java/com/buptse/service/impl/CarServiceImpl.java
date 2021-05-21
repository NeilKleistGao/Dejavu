package com.buptse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptse.mapper.CarMapper;
import com.buptse.pojo.Car;
import com.buptse.service.ICarService;
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

}
