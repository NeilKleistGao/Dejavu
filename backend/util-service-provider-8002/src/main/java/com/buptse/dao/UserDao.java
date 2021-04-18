package com.buptse.dao;


import com.buptse.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    public boolean addUser(User user);

    public User queryById(Integer uid);

    public List<User> queryAll();
}
