package com.example.demo.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Options;

@Mapper
public interface StockMapper {
  @Select("select * from stock where item_id = #{item_id} and shop_id=#{shop_id}")
  Stock selectById(int item_id,int shop_id);

  @Select("select * from stock")
  ArrayList<Stock> selectAllStock();

  @Insert("insert into stock (item_id,shop_id,number) values (#{item_id},#{shop_id},#{number});")

  @Update("update stock set number=#{number} where item_id = #{item_id},shop_id = #{shop_id}")
  void updateById(Stock stock);

  @Delete("delete from stock where item_id = #{item_id},shop_id = #{shop_id}")
  boolean deleteById(Stock stock);
}
