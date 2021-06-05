package com.buptse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buptse.pojo.Car;
import com.buptse.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
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
  @Insert("insert into user (phone_number,mail,name,avatar,password)"
      + "values(#{phone_number},#{mail},#{name},#{avatar},#{password})")
  @Options(useGeneratedKeys = true,keyProperty = "uid",keyColumn = "uid")
  Integer insertUser(User user);

}
