package com.example.demo.model;

import java.util.Date;

public class Chat {
  String name;
  String message;
  
  public String getName(){
    return this.name;  
  }

  public String getMessage(){
    return this.message;  
  }

  public void setName(String name){
    this.name=name;  
  }

  public void setMessage(String msg ){
    this.message=msg;  
  }
}
