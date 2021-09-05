package com.buptse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buptse.pojo.User;
import com.buptse.pojo.UserRole;
import java.util.List;


public interface IUserRoleService{
  int deleteUserRole(UserRole userRole);
  int saveUserRole(UserRole userRole);
  List<UserRole> findUserRole(User user);
}
