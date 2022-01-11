package com.example.demo.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LogMapper {
  @Insert("insert into logs (date,user_id,item_id,send_shop_id,recv_shop_id,number,msg) values (#{date},#{user_id},#{item_id},#{send_shop_id},#{recv_shop_id},#{number},#{msg});")
  @Options(useGeneratedKeys = true, keyColumn = "log_id", keyProperty = "log_id")
  void insertLog(Log log);

  @Select("select * from logs;")
  ArrayList<Log> selectAllLogs();
}
