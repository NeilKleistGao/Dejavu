package com.buptse.common.util;

import java.util.HashMap;
import java.util.Map;

public class CarStateAndCodeUtile {
  private static Map<String,Integer> bodyKeyMap = new HashMap();
  private static Map<Integer,String> codeKeyMap = new HashMap();
  static{
    bodyKeyMap.put("待售",0);
    codeKeyMap.put(0,"待售");
    bodyKeyMap.put("议价",1);
    codeKeyMap.put(1,"议价");
    bodyKeyMap.put("已售",2);
    codeKeyMap.put(2,"已售");
  }
  public static Integer getCodeByBody(String body){
    if(!bodyKeyMap.containsKey(body)){
      return -1;
    }
    return bodyKeyMap.get(body);
  }
  public static String getBodyByCode(Integer code){
    if(!codeKeyMap.containsKey(code)){
      return "未知状态";
    }
    return codeKeyMap.get(code);
  }
}
