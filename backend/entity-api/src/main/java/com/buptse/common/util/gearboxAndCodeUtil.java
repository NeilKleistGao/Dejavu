package com.buptse.common.util;

import java.util.HashMap;
import java.util.Map;

public class gearboxAndCodeUtil {
  private static Map<String,Integer> gearKeyMap = new HashMap();
  private static Map<Integer,String> codeKeyMap = new HashMap();
  static{
    gearKeyMap.put("手动",0);
    codeKeyMap.put(0,"手动");
    gearKeyMap.put("自动",1);
    codeKeyMap.put(1,"自动");
    gearKeyMap.put("未知动力档位",-1);
    codeKeyMap.put(-1,"未知动力档位");
  }
  public static Integer getCodeByGear(String body){
    if(!gearKeyMap.containsKey(body)){
      return -1;
    }
    return gearKeyMap.get(body);
  }
  public static String getGearByCode(Integer code){
    if(!codeKeyMap.containsKey(code)){
      return "未知动力档位";
    }
    return codeKeyMap.get(code);
  }
}
