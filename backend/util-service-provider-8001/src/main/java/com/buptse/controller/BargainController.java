package com.buptse.controller;

import com.buptse.pojo.Bargain;
import com.buptse.service.IBargainService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.ws.rs.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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
}
