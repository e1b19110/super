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

  @Select("select stock.shop_id,shop_name,stock.item_id,item_name,price,number from stock,shop,items where stock.shop_id=shop.shop_id and stock.item_id=items.item_id and stock.shop_id=#{shop_id}")
  Zaiko selectById(int shop_id);

  @Select("select stock.shop_id,shop_name,stock.item_id,item_name,price,number from stock,shop,items where stock.shop_id=shop.shop_id and stock.item_id=items.item_id and not (stock.shop_id = #{shop_id})")
  Zaiko selectByNId(int shop_id);

  @Select("select stock.shop_id,shop_name,stock.item_id,item_name,price,number from stock,shop,items where stock.shop_id=shop.shop_id and stock.item_id=items.item_id")
  ArrayList<Zaiko> selectAllStock();

}
