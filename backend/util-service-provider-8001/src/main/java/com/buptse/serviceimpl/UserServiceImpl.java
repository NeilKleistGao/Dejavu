package com.buptse.serviceimpl;

import com.buptse.dao.UserDao;
import com.buptse.pojo.User;
import com.buptse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public boolean addUser(User user) {
        return  userDao.addUser(user);
    }

    @Override
    public User queryById(Integer id) {
        return userDao.queryById(id);
    }

    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }

}
