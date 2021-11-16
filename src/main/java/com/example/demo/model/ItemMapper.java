package com.example.demo.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ItemMapper {
  @Select("select * from items where item_id = #{item_id}")
  Item selectById(int item_id);

  @Select("select * from items")
  ArrayList<Item> selectAllItems();

  @Insert("insert into items (item_name,price) values (#{item_name},#{price});")
  @Options(useGeneratedKeys = true, keyColumn = "item_id", keyProperty = "item_id")
  void insertItem(Item item);

  @Update("update items set item_name=#{item_name}, price=#{price} where item_id = #{item_id}")
  void updateById(int item_id);

  @Delete("delete from items where id =#{id}")
  boolean deleteById(int id);
}
