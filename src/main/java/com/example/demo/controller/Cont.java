package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.demo.model.Item;
import com.example.demo.model.ItemMapper;
import com.example.demo.model.Shop;
import com.example.demo.model.ShopMapper;
import com.example.demo.model.Stock;
import com.example.demo.model.StockMapper;
import com.example.demo.model.User;
import com.example.demo.model.UserMapper;
import com.example.demo.model.Zaiko;
import com.example.demo.model.ZaikoMapper;
import com.example.demo.model.nSchedule;
import com.example.demo.model.Chat;
import com.example.demo.model.ChatList;
import com.example.demo.service.AsyncChat;
import com.example.demo.model.Log;
import com.example.demo.model.LogMapper;

@RequestMapping("/himiko")
@Controller
public class Cont {
  @Autowired
  ItemMapper imapper;
  @Autowired
  ShopMapper shmapper;
  @Autowired
  StockMapper stmapper;
  @Autowired
  ZaikoMapper zmapper;
  @Autowired
  UserMapper uMapper;
  @Autowired
  LogMapper lMapper;
  @Autowired
  private nSchedule nSchedule;
  @Autowired
  ChatList clist;
  @Autowired
  AsyncChat async;

  @GetMapping("sse")
  public SseEmitter sseChat() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.async.asyncShowMatchList(sseEmitter);
    return sseEmitter;
  }

  @GetMapping("login")
  public String login(ModelMap model, Principal prin) {
    int id = Integer.parseInt(prin.getName());
    User user = uMapper.selectById(id);
    model.addAttribute("name", user.getUser_name());

    return "login.html";
  }

  @GetMapping("zaiko")
  public String zaiko(ModelMap model, Principal prin) {
    int id = Integer.parseInt(prin.getName());
    User user = uMapper.selectById(id);
    ArrayList<Zaiko> zaiko1 = zmapper.selectById(user.getShop_id());
    model.addAttribute("zaiko", zaiko1);
    model.addAttribute("shop_name", zaiko1.get(0).getShop_name());

    return "zaiko.html";
  }

  /*
   * @GetMapping("zaiko") public String zaiko(ModelMap model) { ArrayList<Shop>
   * shoplist = shmapper.selectAllShop(); model.addAttribute("shoplist",
   * shoplist);
   *
   * return "zaiko.html"; }
   *
   * @GetMapping("zaiko/tempo") public String tempo(ModelMap model, @RequestParam
   * int shop_id) { ArrayList<Zaiko> zaiko1 = zmapper.selectById(shop_id);
   * model.addAttribute("zaiko", zaiko1); model.addAttribute("shop_name",
   * zaiko1.get(0).getShop_name());
   *
   * return "zaiko.html"; }
   */

  @GetMapping("nyuka")
  public String nyuka() {
    return "nyuka.html";
  }

  @GetMapping("nyuka1")
  public String nyuka1(ModelMap model) {
    ArrayList<Item> items = imapper.selectAllItems();
    // ArrayList<Shop> shops = shmapper.selectAllShop();
    model.addAttribute("items", items);
    // model.addAttribute("shops", shops);
    return "nyuka1.html";
  }

  @PostMapping("nyuka2")
  public String nyuka2(ModelMap model, @RequestParam Integer item_id, @RequestParam Integer number, Principal prin) {
    Stock item = new Stock();
    int id = Integer.parseInt(prin.getName());
    User user = uMapper.selectById(id);
    item.setItem_id(item_id);
    item.setShop_id(user.getShop_id());
    item.setNumber(number);

    nSchedule.addItems(item);
    model.addAttribute("itemlist", nSchedule);
    ArrayList<Item> items = imapper.selectAllItems();
    // ArrayList<Shop> shops = shmapper.selectAllShop();
    model.addAttribute("items", items);
    // model.addAttribute("shops", shops);

    return "nyuka1.html";
  }

  @GetMapping("nyuka3")
  public String nyuka3(ModelMap model) {
    nSchedule.resetItems();
    ArrayList<Item> items = imapper.selectAllItems();
    // ArrayList<Shop> shops = shmapper.selectAllShop();
    model.addAttribute("items", items);
    // model.addAttribute("shops", shops);
    return "nyuka1.html";
  }

  @GetMapping("nyuka4")
  public String nyuka4(ModelMap model) {
    int flag = 0;
    Stock stock = new Stock();
    ArrayList<Stock> stocklist = stmapper.selectAllStock();
    ArrayList<Stock> nSchedulelist = nSchedule.getItems();
    for (Stock sche : nSchedulelist) {
      for (int i = 0; i < stocklist.size(); i++) {
        if (stocklist.get(i).getItem_id() == sche.getItem_id() && stocklist.get(i).getShop_id() == sche.getShop_id()) {
          flag = 1;
          break;
        }
      }
      if (flag == 0) {
        stmapper.insertItem(sche);
      } else if (flag == 1) {
        stock = stmapper.selectById(sche.getItem_id(), sche.getShop_id());
        int newnumber = stock.getNumber() + sche.getNumber();
        stock.setNumber(newnumber);
        stmapper.updateById(stock);
        flag = 0;
      }
    }
    nSchedule.resetItems();
    ArrayList<Item> items = imapper.selectAllItems();
    // ArrayList<Shop> shops = shmapper.selectAllShop();
    model.addAttribute("items", items);
    // model.addAttribute("shops", shops);
    return "nyuka1.html";
  }

  @GetMapping("chat")
  public String chat() {
    return "chat.html";
  }

  @PostMapping("chat1")
  public String chat1(ModelMap model, @RequestParam String msg, Principal name) {
    Chat chat = new Chat();
    chat.setName(uMapper.selectUserNameByUserId(name.getName()));
    chat.setMessage(msg);
    ArrayList<Chat> chatlist = this.async.addChat(chat);
    model.addAttribute("chats", chatlist);
    return "chat.html";
  }

  @GetMapping("syukka1")
  public String syukka1(ModelMap model, Principal prin) {
    int id = Integer.parseInt(prin.getName());
    User user = uMapper.selectById(id);
    ArrayList<Zaiko> zaikolist = zmapper.selectById(user.getShop_id());
    model.addAttribute("zaikolist", zaikolist);
    ArrayList<Shop> shops = shmapper.selectAllShop();
    model.addAttribute("shops", shops);
    return "syukka.html";
  }

  @PostMapping("syukka2")
  public String syukka2(ModelMap model, @RequestParam Integer item_id, Principal prin, @RequestParam Integer number,
      @RequestParam Integer recv_shop_id, @RequestParam String msg) {
    int flag = 0;
    int tmp = 0;
    String print = "結果なし";
    Stock item = new Stock();
    int id = Integer.parseInt(prin.getName());
    User user = uMapper.selectById(id);

   /* if (item_id == null || number == null || recv_shop_id == null || msg == null) {
      String msgError = "全ての空欄を埋めてください";
      model.addAttribute("msge", msgError);
      return "syukka.html";
    }*/

    item.setItem_id(item_id);
    item.setShop_id(user.getShop_id());
    item.setNumber(number);
    ArrayList<Stock> stocklist = stmapper.selectAllStock();
    for (Stock stc : stocklist) {
      if (item.getItem_id() == stc.getItem_id() && item.getShop_id() == stc.getShop_id()) {
        if (item.getNumber() <= stc.getNumber()) {
          tmp = stc.getNumber() - item.getNumber();
          item.setNumber(tmp);
          stmapper.updateById(item);
          flag = 1;
        } else {
          flag = 2;
        }
        break;
      } else {
        flag = 3;
      }
    }
    if (flag == 1) {
      print = "出荷完了しました";
    } else if (flag == 2) {
      print = "エラー 在庫数より出荷数の方が多いです";

    } else if (flag == 3) {
      print = "エラー 商品が存在しません";
    }
    model.addAttribute("result", print);
    ArrayList<Zaiko> zaikolist = zmapper.selectById(user.getShop_id());
    model.addAttribute("zaikolist", zaikolist);
    ArrayList<Shop> shops = shmapper.selectAllShop();
    model.addAttribute("shops", shops);
    Date date = new Date(); // 今日の日付
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String strDate = dateFormat.format(date);
    Log log = new Log();
    log.setDate(strDate);
    log.setUser_id(id);
    log.setItem_id(item_id);
    log.setSend_shop_id(user.getShop_id());
    log.setRecv_shop_id(recv_shop_id);
    log.setNumber(number);
    log.setMsg(msg);
    lMapper.insertLog(log);
    return "syukka.html";
  }

  @GetMapping("denpyo")
  public String denpyo(ModelMap model) {
    ArrayList<Log> logs = lMapper.selectAllLogs();
    model.addAttribute("logs", logs);
    return "denpyo.html";
  }
}
