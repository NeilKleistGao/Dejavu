package com.buptse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptse.dto.CommonResult;
import com.buptse.pojo.Bargain;
import com.buptse.service.IBargainService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.ws.rs.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class BargainController {
  @Autowired
  IBargainService bargainService;

  @PostMapping("/transaction/bargain/new")
  public Map<String, Boolean> createBargain(
      @RequestParam(value = "uid")Integer uid,
      @RequestParam(value = "car_id")Integer car_id,
      @RequestParam(value = "price")BigDecimal price,
      @RequestParam(value = "start_time")LocalDateTime start_time,
      @RequestParam(value = "end_time")LocalDateTime end_time
  ){
    Bargain bargain = new Bargain();
    bargain.setCar_id(car_id);
    bargain.setUid(uid);
    bargain.setPrice(price);
    bargain.setStart_time(start_time);
    bargain.setEnd_time(end_time);
    boolean result = bargainService.save(bargain);
    final HashMap<String, Boolean> map = new HashMap<String, Boolean>();
    map.put("result",result);
    return map;
  }

  /**
   * 根据条件查询砍价记录
   * TODO  增加token校验
   * @param uid
   * @param barginId
   * @param carID
   * @return
   */
  @PostMapping("/transaction/bargain/query")
  public CommonResult queryBargain(
          @RequestParam("id") Integer uid,
          @RequestParam(value = "uid",required = false) Integer barginId,
          @RequestParam(value = "cid",required = false) Integer carID){
    QueryWrapper<Bargain> bargainQueryWrapper = new QueryWrapper<>();
    if(barginId!=null){bargainQueryWrapper.eq("uid",barginId);}
    if(carID!=null){bargainQueryWrapper.eq("carid",carID);}
    List<Bargain> bargains = bargainService.list(bargainQueryWrapper);
    return CommonResult.success(bargains);

  }



}
