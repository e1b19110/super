package com.example.demo.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
  @Select("select * from users where user_id = #{user_id}")
  User selectById(int user_id);

  @Select("select * from users where shop_id = #{shop_id}")
  User selectByShopId(int shop_id);
}
