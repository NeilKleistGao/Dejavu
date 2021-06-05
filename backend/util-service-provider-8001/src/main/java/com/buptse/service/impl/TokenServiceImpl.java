package com.buptse.service.impl;

import com.buptse.auth.util.RedisUtil;
import com.buptse.auth.util.TokenUtil;
import com.buptse.pojo.UserToken;
import com.buptse.service.ITokenService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

// Token存在redis中，暂未完成
@Service
public class TokenServiceImpl implements ITokenService {

  private static final Long expireTime = 3600 * 2L;
  @Autowired
  RedisUtil redisUtil;
  @Autowired
  private RedisTemplate redisTemplate;
  @Override
  public UserToken findUserById(Integer Id) {
    String token = (String) redisUtil.hget("User",String.valueOf(Id));
    long expire = redisUtil.getExpire(String.valueOf(Id));
    if(token != null ){
      UserToken userToken = new UserToken();
      userToken.setUserId(Id);
      userToken.setToken(token);
      userToken.setExpireTime(expire);
      return userToken;
    }
    return null;
  }

  @Override
  public boolean savaToken(UserToken userToken) {
    String token = userToken.getFullToken();
    String userId = String.valueOf(userToken.getUserId());
    System.out.println(token);
    boolean flag = redisUtil.hset("User",userId,token,userToken.getExpireTime());
    return flag;
  }

  @Override
  public boolean deleteToken(String token) {
    token.split(TokenUtil.TOKEN_SPLITER);
    redisUtil.del(token);
    return false;
  }
}
