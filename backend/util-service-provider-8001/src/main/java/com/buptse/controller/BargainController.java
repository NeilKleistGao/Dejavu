package com.buptse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptse.common.RESULT;
import com.buptse.dto.CommonResult;
import com.buptse.pojo.Bargain;
import com.buptse.service.IBargainService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.buptse.service.ITokenService;
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
  public  Map<String, Boolean> createBargain(
          @RequestBody Bargain bargainDto
  ){
    boolean result = bargainService.save(bargainDto);
    final HashMap<String, Boolean> map = new HashMap<String, Boolean>();
    map.put("result",result);
    return map;
  }

  /**
   * 根据条件查询砍价记录
   * @return
   */
  @PostMapping("/transaction/bargain/query")
  public CommonResult queryBargain(
          @RequestBody BarginQueryDto queryDto){

    if(tokenService.findUserById(queryDto.getId())==null){
      return CommonResult.failFast(RESULT.USER_NOT_LOGIN);
    }
    QueryWrapper<Bargain> bargainQueryWrapper = new QueryWrapper<>();
    if(queryDto.getUid()!=null){bargainQueryWrapper.eq("uid",queryDto.getUid());}
    if(queryDto.getCid()!=null){bargainQueryWrapper.eq("carid",queryDto.getCid());}
    List<Bargain> bargains = bargainService.list(bargainQueryWrapper);
    return CommonResult.success(bargains);

  }



}
