package com.example.demo.model;

import java.util.Date;

public class Log {
  int log_id;
  Date date;
  int user_id;
  int item_id;
  int send_shop_id;
  int recv_shop_id;
  int number;
  String msg;

  public int getLog_id() {
    return log_id;
  }
  public void setLog_id(int log_id) {
    this.log_id = log_id;
  }
  public int getUser_id() {
    return user_id;
  }
  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }
  public int getItem_id() {
    return item_id;
  }
  public void setItem_id(int item_id) {
    this.item_id = item_id;
  }
  public int getSend_shop_id() {
    return send_shop_id;
  }
  public void setSend_shop_id(int send_shop_id) {
    this.send_shop_id = send_shop_id;
  }
  public int getRecv_shop_id() {
    return recv_shop_id;
  }
  public void setRecv_shop_id(int recv_shop_id) {
    this.recv_shop_id = recv_shop_id;
  }
  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public String getMsg() {
    return msg;
  }
  public void setMsg(String msg) {
    this.msg = msg;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }


}
