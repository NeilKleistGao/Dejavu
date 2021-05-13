package com.buptse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptse.mapper.UserMapper;
import com.buptse.pojo.User;
import com.buptse.service.IUserService;
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

}
