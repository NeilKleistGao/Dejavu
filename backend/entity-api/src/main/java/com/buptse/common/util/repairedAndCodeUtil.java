package com.buptse.common.util;

import java.util.HashMap;
import java.util.Map;

public class repairedAndCodeUtil {
  private static Map<String,Integer> repairedKeyMap = new HashMap();
  private static Map<Integer,String> codeKeyMap = new HashMap();
  static{
    repairedKeyMap.put("有尚未修复的损坏",0);
    codeKeyMap.put(0,"有尚未修复的损坏");
    repairedKeyMap.put("无尚未修复的损坏",1);
    codeKeyMap.put(1,"无尚未修复的损坏");
    repairedKeyMap.put("无法确定损坏",-1);
    codeKeyMap.put(-1,"无法确定损坏");
  }
  public static Integer getCodeByRequired(String body){
    if(repairedKeyMap.containsKey(body)){
      return -1;
    }
    return repairedKeyMap.get(body);
  }
  public static String getRequiredByCode(Integer code){
    if(codeKeyMap.containsKey(code) == false){
      return "无法确认损坏";
    }
    return codeKeyMap.get(code);
  }
}
