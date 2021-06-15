package com.buptse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptse.common.RESULT;
import com.buptse.dto.CommonResult;
import com.buptse.pojo.Bargain;
import com.buptse.pojo.User;
import com.buptse.service.IBargainService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.buptse.service.ITokenService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.buptse.dto.BarginQueryDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class BargainController {
  @Autowired
  private IBargainService bargainService;

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
    boolean result = bargainService.save(bargainDto);
    final HashMap<String, Boolean> map = new HashMap<String, Boolean>();
    map.put("result",result);
    return CommonResult.success(map);
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
