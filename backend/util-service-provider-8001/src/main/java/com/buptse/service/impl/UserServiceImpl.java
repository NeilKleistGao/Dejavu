package com.buptse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptse.mapper.UserMapper;
import com.buptse.pojo.User;
import com.buptse.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ASJA
 * @since 2021-04-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

  @Autowired
  UserMapper userMapper;

  @Override
  public User findByName(String username) {
    return userMapper.findUserByName(username);
  }

  @Override
  public User findByPhoneNumber(String phoneNumber) {
    return userMapper.findUserByPhoneNumber(phoneNumber);
  }

  @Override
  public Integer insertUser(User user) {
    return userMapper.insertUser(user);
  }
}
