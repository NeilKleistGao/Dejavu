package com.buptse.service;

import com.buptse.pojo.UserToken;

public interface ITokenService {
  UserToken findUserById(Integer Id);
  boolean savaToken(UserToken userToken);
  boolean deleteToken(String token);
}
