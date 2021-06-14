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
  /**
  * @Description: create a new token when token has expired
  * @Param: [userId]
  * @return: java.util.Map<java.lang.String,java.lang.Object>
  * @Author: gerayking
  * @Date: 2021/6/5-14:38
  */
  public Map<String, Object> createToken(Integer userId) {
    Map<String, Object>res = new HashMap<>();
    String token = TokenGenerator.generateValue();
    UserToken userToken = tokenService.findUserById(userId);
    if (null == userToken){
       userToken = new UserToken();
      userToken.setExpireTime(EXPIRE);
      userToken.setUserId(userId);
      userToken.setToken(token);
      tokenService.savaToken(userToken);
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
  /**
  * @Description: logout when user token certificated in redis.
  * @Param: [token]
  * @return: boolean
  * @Author: gerayking
  * @Date: 2021/6/14-21:11
  */
  public boolean logout(String token) {
    final String[] split = token.split(TokenUtil.TOKEN_SPLITER);
    final UserToken userToken = tokenService.findUserById(Integer.parseInt(split[1]));
    if(userToken == null ||!userToken.getFullToken().equals(token)){
      return false;
    }
    return tokenService.deleteToken(token);
  }

  /**
  * @param accessToken
  * @return Nothing.
  * @author gerayking
  * @date 2021/6/5 14:31
   * 通过传送过来的token来验证用户的token是否正确，正确返回token，否则返回null
  */
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

  /**
  * @param userId Unused.
  * @return Nothing.
  * @author gerayking
  * @date 2021/6/5 14:33
   * 通过userId查询redis来获取token
  */
  @Override
  public User findByUserId(Integer userId) {
    return userService.getById(userId);
  }

  /**
  * @param phoneNumber Unused.
  * @return Nothing.
  * @author gerayking
  * @date 2021/6/5 14:33
   * 通过phoneNumber来获取用户资料
  */
  @Override
  public User findByUserPhone(String phoneNumber) {
    return userService.findByPhoneNumber(phoneNumber);
  }
}
