package com.buptse.auth;

import com.alibaba.fastjson.JSON;
import com.buptse.common.RESULT;
import com.buptse.dto.CommonResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public  class ShiroException {
  @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
  public CommonResult authenticationException(HttpServletRequest request, HttpServletResponse response) {
    Map result = new HashMap<String, String>();
    result.put("status","-1");
    result.put("message","未登录");
    writeJson(result,response);
    return null;
  }
  @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
  public CommonResult authorizationException(HttpServletRequest request, HttpServletResponse response) {
    Map result = new HashMap<String, String>();
    result.put("status","415");
    result.put("message","无权限");
    writeJson(result,response);
    return null;
  }
  private void writeJson(Map<String, Object> map, HttpServletResponse response) {
    PrintWriter out = null;
    try {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("application/json; charset=utf-8");
      out = response.getWriter();
      out.write(JSON.toJSONString(map));
    } catch (IOException e) {
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

}
