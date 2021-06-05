package com.buptse.service.impl;

import com.buptse.auth.TokenGenerator;
import com.buptse.auth.util.TokenUtil;
import com.buptse.pojo.User;
import com.buptse.pojo.UserToken;
import com.buptse.service.IShiroService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroServiceImpl implements IShiroService {
  private final static Long EXPIRE = 3600 * 2L; // 两小时失效
  @Autowired
  private UserServiceImpl userService;
  @Autowired
  private TokenServiceImpl tokenService;
  @Override
  public User findByUsername(String username) {
    return userService.findByName(username);
  }

  @Override
  public Map<String, Object> createToken(Integer userId) {
    Map<String, Object>res = new HashMap<>();
    String token = TokenGenerator.generateValue();
    UserToken userToken = tokenService.findUserById(userId);
    if (null == userToken){
      final UserToken newUserToken = new UserToken();
      newUserToken.setExpireTime(EXPIRE);
      newUserToken.setUserId(userId);
      newUserToken.setToken(token);
      tokenService.savaToken(newUserToken);
    }else{
      userToken.setToken(token);
      userToken.setExpireTime(EXPIRE);
      tokenService.savaToken(userToken);
    }
    res.put("token",userToken.getFullToken());
    res.put("expire",LocalDateTime.now().plusSeconds(EXPIRE));
    return res;
  }

  @Override
  public void logout(String token) {
    tokenService.deleteToken(token);
  }

  @Override
  public UserToken findByToken(String accessToken) {
    final String[] split = accessToken.split(TokenUtil.TOKEN_SPLITER);
    if(split.length<2){
      return null;
    }
    Integer userId = Integer.parseInt(split[1]);
    final UserToken userToken = tokenService.findUserById(userId);
    return userToken;
  }

  @Override
  public User findByUserId(Integer userId) {
    return userService.getById(userId);
  }

  @Override
  public User findByUserPhone(String phoneNumber) {
    return userService.findByPhoneNumber(phoneNumber);
  }
}
