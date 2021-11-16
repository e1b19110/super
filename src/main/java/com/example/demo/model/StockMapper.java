package com.example.demo.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface StockMapper {
  @Select("select * from stock where stock_id=#{stock_id}")
  Stock selectById(int stock_id);

  @Select("select * from stock")
  ArrayList<Stock> selectAllStock();

  @Insert("insert into stock (item_id,shop_id,number) values (#{item_id},#{shop_id},#{number});")
  @Options(useGeneratedKeys = true, keyColumn = "stock_id", keyProperty = "stock_id")
  void insertItem(Stock stock);

  @Update("update stock set number=#{number} where stock_id = #{stock_id}")
  void updateById(int stock_id);

  @Delete("delete from stock where stock_id = #{stock_id}")
  boolean deleteById(int stock_id);
}
