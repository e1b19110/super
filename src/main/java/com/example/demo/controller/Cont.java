package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Item;
import com.example.demo.model.ItemMapper;
import com.example.demo.model.Shop;
import com.example.demo.model.ShopMapper;
import com.example.demo.model.Stock;
import com.example.demo.model.StockMapper;
import com.example.demo.model.Zaiko;
import com.example.demo.model.ZaikoMapper;

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
  public String nyuka2(@RequestParam Integer item_id, @RequestParam Integer shop_id, @RequestParam Integer number) {
    int flag = 0;
    Stock stock = new Stock();
    ArrayList<Stock> stocklist = stmapper.selectAllStock();
    for (int i = 0; i < stocklist.size(); i++) {
      if (stocklist.get(i).getItem_id() == item_id && stocklist.get(i).getShop_id() == shop_id) {
        flag = 1;
        break;
      }
    }
    if (flag == 0) {
      stock.setItem_id(item_id);
      stock.setShop_id(shop_id);
      stock.setNumber(number);
      stmapper.insertItem(stock);
    } else if (flag == 1) {
      stock = stmapper.selectById(item_id, shop_id);
      int newnumber = stock.getNumber() + number;
      stock.setNumber(newnumber);
      stmapper.updateById(stock);
    }

    return "nyuka1.html";
  }

}
