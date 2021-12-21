package com.example.demo.service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.demo.model.Chat;
import com.example.demo.model.ChatList;

@Service
public class AsyncChat {

  @Autowired
  ChatList clist;

  boolean ChatUpdated = false;

  @Async
  public void asyncShowMatchList(SseEmitter emitter) {
    ChatUpdated = true;
    try {
      while (true) {
        if (false == ChatUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }
        ArrayList<Chat> chatlist = clist.getChatlist();
        emitter.send(chatlist);
        TimeUnit.MILLISECONDS.sleep(1000);
        ChatUpdated = false;
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncShowMatchList complete");
  }

}
