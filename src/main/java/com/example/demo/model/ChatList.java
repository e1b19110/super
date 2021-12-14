package com.example.demo.model;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class ChatList {
  ArrayList<Chat> chatlist = new ArrayList<>();

  public void addChat(Chat chat) {
    this.chatlist.add(chat);
  }

  public ArrayList<Chat> getChatlist() {
    return chatlist;
  }

  public void setChatlist(ArrayList<Chat> chatlist) {
    this.chatlist = chatlist;
  }

}
