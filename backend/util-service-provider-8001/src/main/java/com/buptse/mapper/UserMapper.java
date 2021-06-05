package com.buptse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buptse.pojo.User;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ASJA
 * @since 2021-04-23
 */
public interface UserMapper extends BaseMapper<User> {
  @Select("select * from user where username=#{userName}")
  User findUserByName(String userName);
  @Select("select * from user where phone_number=#{phoneNumber}")
  User findUserByPhoneNumber(String PhoneNumber);
}
