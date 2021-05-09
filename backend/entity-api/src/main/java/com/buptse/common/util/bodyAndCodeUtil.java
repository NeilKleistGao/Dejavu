package com.buptse.common.util;

import java.util.HashMap;
import java.util.Map;

public class bodyAndCodeUtil {
  private static Map<String,Integer> bodyKeyMap = new HashMap();
  private static Map<Integer,String> codeKeyMap = new HashMap();
  static{
    bodyKeyMap.put("豪华轿车",0);
    codeKeyMap.put(0,"豪华轿车");
    bodyKeyMap.put("微型车",1);
    codeKeyMap.put(1,"微型车");
    bodyKeyMap.put("厢型车",2);
    codeKeyMap.put(2,"厢型车");
    bodyKeyMap.put("大巴车",3);
    codeKeyMap.put(3,"大巴车");
    bodyKeyMap.put("敞篷车",4);
    codeKeyMap.put(4,"敞篷车");
    bodyKeyMap.put("双门汽车",5);
    codeKeyMap.put(5,"双门汽车");
    bodyKeyMap.put("商务车",6);
    codeKeyMap.put(6,"商务车");
    bodyKeyMap.put("搅拌车",7);
    codeKeyMap.put(7,"搅拌车");
  }
  public static Integer getCodeByBody(String body){ return bodyKeyMap.get(body); }
  public static String getBodyByCode(Integer code){
    return codeKeyMap.get(code);
  }
}
