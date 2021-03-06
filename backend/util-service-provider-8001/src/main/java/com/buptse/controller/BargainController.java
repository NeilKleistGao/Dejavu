package com.buptse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptse.common.RESULT;
import com.buptse.common.util.CarStateAndCodeUtile;
import com.buptse.dto.CommonResult;
import com.buptse.pojo.Bargain;
import com.buptse.pojo.Car;
import com.buptse.pojo.User;
import com.buptse.service.IBargainService;
import com.buptse.service.ICarService;
import java.util.HashMap;
import java.util.List;

import com.buptse.service.ITokenService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import com.buptse.dto.BarginQueryDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class BargainController {
  @Autowired
  private IBargainService bargainService;

  @Autowired
  private ICarService carService;

  @Autowired
  private ITokenService tokenService;

  @PostMapping("/transaction/bargain/new")
  public CommonResult createBargain(
          @RequestBody Bargain bargainDto
  ){
    final Subject subject = SecurityUtils.getSubject();
    if(!subject.isAuthenticated()){
      return CommonResult.failFast(RESULT.USER_NOT_LOGIN);
    }
    final Integer carId = bargainDto.getCar_id();
    final Car car = carService.getById(carId);
    final User user = (User)subject.getPrincipal();
    bargainDto.setUid(user.getUid());
    if(user.getUid().equals(car.getUid())){
      return CommonResult.failFast(RESULT.CAN_NOT_BUY_YOURSELF_CAR);
    }
    if(car.getState()  == 2){
      return CommonResult.failFast(RESULT.CAN_NOT_BARGAIN_WITH_SELLED_CAR);
    }
    boolean result = bargainService.save(bargainDto);
    car.setState(CarStateAndCodeUtile.getCodeByState("议价"));
    final boolean isSuccess = carService.updateById(car);
    result &= isSuccess;
    return CommonResult.success(result);
  }

  /**
   * 根据条件查询砍价记录
   * @return
   */
  @PostMapping("/transaction/bargain/query")
  /**
  * @Description: user only query himself or herself bargins, manager can query all bargains.
  * @Param: [queryDto]
  * @return: com.buptse.dto.CommonResult
  * @Author: gerayking
  * @Date: 2021/6/15-9:00
  */
  public CommonResult queryBargain(
          @RequestBody BarginQueryDto queryDto){
    final Subject subject = SecurityUtils.getSubject();
    final User user = (User)subject.getPrincipal();
    if(!subject.isAuthenticated()){
      return CommonResult.failFast(RESULT.USER_NOT_LOGIN);
    }
    if((!user.getUid().equals(queryDto.getUid())) && !subject.hasRole("manager")){
      return CommonResult.failFast(RESULT.NO_PERMISSION);
    }
    QueryWrapper<Bargain> bargainQueryWrapper = new QueryWrapper<>();
    if(queryDto.getUid()!=null){bargainQueryWrapper.eq("uid",queryDto.getUid());}
    if(queryDto.getCid()!=null){bargainQueryWrapper.eq("car_id",queryDto.getCid());}
    List<Bargain> bargains = bargainService.list(bargainQueryWrapper);
    return CommonResult.success(bargains);

  }



}
