package com.buptse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buptse.pojo.User;
import com.buptse.pojo.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserRoleMapper{
  @Delete("delete from userrole where user_id = #{UserId} and role=#{role}")
  int delete(UserRole userRole);

  @Insert("insert into userrole (user_id,role) values(#{UserId},#{role})")
  int sava(UserRole userRole);

  @Select("select * from userrole where user_id=#{uid}")
  List<UserRole> findUserRole(User user);
}
