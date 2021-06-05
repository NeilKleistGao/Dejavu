package com.buptse.service;

import com.buptse.pojo.User;
import com.buptse.pojo.UserToken;
import java.util.Map;

import java.util.Map;

/**
 * @Author 大誌
 * @Date 2019/3/30 22:18
 * @Version 1.0
 */
public interface IShiroService {
  /**
   * Find user by username
   * @param username
   * @return
   */
  User findByUsername(String username);

  /**
   * create token by userId
   * @param userId
   * @return
   */
  Map<String,Object> createToken(Integer userId);

  /**
   * logout
   * @param token
   */
  void logout(String token);

  /**
   * find token by token
   * @param accessToken
   * @return
   */
  UserToken findByToken(String accessToken);

  /**
   * find user by userId
   * @param userId
   * @return
   */
  User findByUserId(Integer userId);

  User findByUserPhone(String phoneNumber);
}
