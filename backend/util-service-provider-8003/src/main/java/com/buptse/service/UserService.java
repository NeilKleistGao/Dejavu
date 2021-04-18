package com.buptse.service;

import com.buptse.pojo.User;

import java.util.List;

public interface UserService {
    public boolean addUser(User user);

    public User queryById(Integer id);

    public List<User> queryAll();
}
