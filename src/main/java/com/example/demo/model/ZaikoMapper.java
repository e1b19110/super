package com.example.demo.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Update;
//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Options;

@Mapper
public interface ZaikoMapper {

  @Select("select * from stock natural join shop natural join items where shop.shop_id=#{shop_id}")
  ArrayList<Zaiko> selectById(int shop_id);

  @Select("select * from stock natural join shop natural join items where not (shop.shop_id=#{shop_id})")
  ArrayList<Zaiko> selectByNId(int shop_id);

  @Select("select * from stock natural join shop natural join items")
  ArrayList<Zaiko> selectAllStock();

}
