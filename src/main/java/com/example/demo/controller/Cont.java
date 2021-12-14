package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

import com.example.demo.model.Item;
import com.example.demo.model.ItemMapper;
import com.example.demo.model.Shop;
import com.example.demo.model.ShopMapper;
import com.example.demo.model.Stock;
import com.example.demo.model.StockMapper;
import com.example.demo.model.Zaiko;
import com.example.demo.model.ZaikoMapper;
import com.example.demo.model.nSchedule;
import com.example.demo.model.Chat;

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
  private nSchedule nSchedule;
  @Autowired
  ArrayList<Chat> chatlist=new ArrayList();

  @GetMapping("login")
  public String login() {
    return "login.html";
  }

  @GetMapping("zaiko")
  public String zaiko(ModelMap model) {
    ArrayList<Shop> shoplist = shmapper.selectAllShop();
    model.addAttribute("shoplist", shoplist);

    return "zaiko.html";
  }

  @GetMapping("zaiko/tempo")
  public String tempo(ModelMap model, @RequestParam int shop_id) {
    ArrayList<Zaiko> zaiko1 = zmapper.selectById(shop_id);
    model.addAttribute("zaiko", zaiko1);
    model.addAttribute("shop_name", zaiko1.get(0).getShop_name());

    return "zaiko.html";
  }

  @GetMapping("nyuka")
  public String nyuka() {
    return "nyuka.html";
  }

  @GetMapping("nyuka1")
  public String nyuka1() {
    return "nyuka1.html";
  }

  @PostMapping("nyuka2")
  public String nyuka2(ModelMap model, @RequestParam Integer item_id, @RequestParam Integer shop_id,
      @RequestParam Integer number) {
    Stock item = new Stock();
    item.setItem_id(item_id);
    item.setShop_id(shop_id);
    item.setNumber(number);

    nSchedule.addItems(item);
    model.addAttribute("itemlist", nSchedule);

    return "nyuka1.html";
  }

  @GetMapping("nyuka3")
  public String nyuka3() {
    nSchedule.resetItems();
    return "nyuka1.html";
  }

  @GetMapping("nyuka4")
  public String nyuka4() {
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
    return "nyuka1.html";
  }

  @GetMapping("chat")
  public String chat() {
    return "chat.html";
  }

  @PostMapping("chat1")
  public String chat1(ModelMap model, @RequestParam String msg,Principal name) {
    Chat chat=new Chat();
    chat.setName(name.getName());
    chat.setMessage(msg);
    chatlist.add(chat);
    model.addAttribute("chatlist",chatlist);
    return "chat.html";
  }
}
