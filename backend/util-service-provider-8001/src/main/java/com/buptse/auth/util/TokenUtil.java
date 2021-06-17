package com.buptse.auth.util;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class TokenUtil {
  /**
   * 获取请求的token
   */
  public static final String TOKEN_SPLITER  = "_";

  public static String getRequestToken(HttpServletRequest httpRequest) {

    //从header中获取token
    String token = httpRequest.getHeader("token");
    //如果header中不存在token，则从参数中获取token
    if (StringUtils.isBlank(token)) {
      token = httpRequest.getParameter("token");
    }
    return token;
  }
}