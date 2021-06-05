package com.buptse.service.impl;

import com.buptse.mapper.UserRoleMapper;
import com.buptse.pojo.User;
import com.buptse.pojo.UserRole;
import com.buptse.service.IUserRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements IUserRoleService {

  @Autowired
  UserRoleMapper userRoleMapper;
  @Override
  public int deleteUserRole(UserRole userRole) {
    return userRoleMapper.delete(userRole);
  }

  @Override
  public int saveUserRole(UserRole userRole) {
    return userRoleMapper.sava(userRole);
  }

  @Override
  public List<UserRole> findUserRole(User user) {
    return userRoleMapper.findUserRole(user);
  }

}
