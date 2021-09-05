package com.buptse.auth;


import org.apache.shiro.authc.UsernamePasswordToken;

public class AuthToken extends UsernamePasswordToken {
  private String token;

  public AuthToken(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  @Override
  public Object getPrincipal() {
    return token;
  }

  @Override
  public Object getCredentials() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
