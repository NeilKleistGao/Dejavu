package com.buptse.common.util;

import java.io.UnsupportedEncodingException;
import org.springframework.util.DigestUtils;

public class PasswordUtil {
  public static String encrypt2MD5(String password) throws UnsupportedEncodingException {
    return DigestUtils.md5DigestAsHex(password.getBytes("UTF-8"));
  }
}
