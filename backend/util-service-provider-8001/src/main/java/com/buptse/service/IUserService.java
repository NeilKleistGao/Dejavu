package com.buptse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buptse.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ASJA
 * @since 2021-04-23
 */
public interface IUserService extends IService<User> {
  User findByName(String username);
  User findByPhoneNumber(String phoneNumber);
  Integer insertUser(User user);
  Boolean isExistPhoneNumber(String phoneNumber);
}
