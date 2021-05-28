package com.buptse.common.util;

import java.util.HashMap;
import java.util.Map;

public class fuelAndCodeUtil {
  private static Map<String,Integer> fuelKeyMap = new HashMap();
  private static Map<Integer,String> codeKeyMap = new HashMap();
  static{
    fuelKeyMap.put("汽油",0);
    codeKeyMap.put(0,"汽油");
    fuelKeyMap.put("柴油",1);
    codeKeyMap.put(1,"柴油");
    fuelKeyMap.put("液化石油气",2);
    codeKeyMap.put(2,"液化石油气");
    fuelKeyMap.put("天然气",3);
    codeKeyMap.put(3,"天然气");
    fuelKeyMap.put("混合动力",4);
    codeKeyMap.put(4,"混合动力");
    fuelKeyMap.put("其他",5);
    codeKeyMap.put(5,"其他");
    fuelKeyMap.put("电动",6);
    codeKeyMap.put(6,"电动");
    fuelKeyMap.put("未知动力类型",-1);
    codeKeyMap.put(-1,"未知动力类型");
  }
  public static Integer getCodeByFuel(String body){
    if(fuelKeyMap.containsKey(body) == false){
      return -1;
    }
    return fuelKeyMap.get(body);
  }
  public static String getFuelByCode(Integer code){
    if(codeKeyMap.containsKey(code) == false){
      return "未知动力类型";
    }
    return codeKeyMap.get(code);
  }
}
