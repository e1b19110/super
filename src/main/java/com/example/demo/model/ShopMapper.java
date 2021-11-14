package com.example.demo.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ShopMapper {
  @Select("select * from shop where shop_id = #{shop_id}")
  Shop selectById(int shop_id);

  @Select("select * from shop")
  ArrayList<Shop> selectAllShop();

  @Insert("insert into shop (shop_name) values (#{shop_name});")
  @Options(useGeneratedKeys = true, keyColumn = "shop_id", keyProperty = "shop_id")
  void insertItem(Shop shop);

  @Update("update shop set shop_name=#{shop_name} where shop_id = #{shop_id}")
  void updateById(int shop_id);

  @Delete("delete from shop where shop_id =#{shop_id}")
  boolean deleteById(int shop_id);
}
