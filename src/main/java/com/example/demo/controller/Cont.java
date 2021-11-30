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
  public String tempo(ModelMap model, @RequestParam int shop_id){
    ArrayList<Zaiko> zaiko1 = zmapper.selectById(shop_id);
    model.addAttribute("zaiko", zaiko1);
    model.addAttribute("shop_name", zaiko1.get(0).getShop_name());

    return "zaiko.html";
  }

  @GetMapping("nyuka")
  public String nyuka() {
    return "nyuka.html";
  }
}
