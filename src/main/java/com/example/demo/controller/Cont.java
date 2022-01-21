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
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.security.Principal;

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
import com.example.demo.model.Log;
import com.example.demo.model.LogMapper;
import com.example.demo.service.AsyncChat;

@RequestMapping("/himiko")
@Controller
public class Cont {
  @Autowired
  ItemMapper itemMapper;
  @Autowired
  ShopMapper shopMapper;
  @Autowired
  StockMapper stockMapper;
  @Autowired
  ZaikoMapper zaikoMapper;
  @Autowired
  UserMapper userMapper;
  @Autowired
  LogMapper logMapper;
  @Autowired
  private nSchedule nSchedule;
  @Autowired
  ChatList chatList;
  @Autowired
  AsyncChat asyncChat;

  @GetMapping("sse")
  public SseEmitter sseChat() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.asyncChat.asyncShowMatchList(sseEmitter);
    return sseEmitter;
  }

  @GetMapping("home")
  public String home(ModelMap model, Principal prin) {
    int id = Integer.parseInt(prin.getName());
    User user = userMapper.selectById(id);
    model.addAttribute("name", user.getUser_name());
    return "home.html";
  }

  @GetMapping("zaiko")
  public String zaiko(ModelMap model, Principal prin) {
    int id = Integer.parseInt(prin.getName());
    User user = userMapper.selectById(id);
    ArrayList<Zaiko> zaikoList = zaikoMapper.selectById(user.getShop_id());
    model.addAttribute("zaiko", zaikoList);
    model.addAttribute("shop_name", zaikoList.get(0).getShop_name());
    return "zaiko.html";
  }

  @GetMapping("nyuka")
  public String nyuka() {
    return "nyuka.html";
  }

  @GetMapping("nyuka1")
  public String nyuka1(ModelMap model) {
    ArrayList<Item> items = itemMapper.selectAllItems();
    model.addAttribute("items", items);

    if (!nSchedule.emptyItems()) {
      model.addAttribute("itemlist", nSchedule);
    }
    return "nyuka1.html";
  }

  @PostMapping("nyuka2")
  public String nyuka2(ModelMap model, @RequestParam Integer item_id, @RequestParam Integer number, Principal prin) {
    Stock stock = new Stock();
    int id = Integer.parseInt(prin.getName());
    User user = userMapper.selectById(id);

    ArrayList<Item> items = itemMapper.selectAllItems();
    model.addAttribute("items", items);

    // 入荷指定された商品が存在するか確認
    boolean flag = false;
    ArrayList<Item> itemlist = itemMapper.selectAllItems();
    for (Item item : itemlist) {
      if (item.getItem_id() == item_id) {
        flag = true;
        break;
      }
    }
    if (!flag) {
      String print = "エラー 商品が存在しません";
      model.addAttribute("result", print);
      return "nyuka1.html";
    }

    stock.setItem_id(item_id);
    stock.setShop_id(user.getShop_id());
    stock.setNumber(number);
    nSchedule.addItems(stock);

    if (!nSchedule.emptyItems()) {
      model.addAttribute("itemlist", nSchedule);
    }
    return "nyuka1.html";
  }

  @GetMapping("nyuka3")
  public String nyuka3(ModelMap model) {
    nSchedule.resetItems();
    ArrayList<Item> items = itemMapper.selectAllItems();
    model.addAttribute("items", items);
    return "nyuka1.html";
  }

  @GetMapping("nyuka4")
  public String nyuka4(ModelMap model) {
    ArrayList<Item> items = itemMapper.selectAllItems();
    model.addAttribute("items", items);

    boolean flag = false;
    ArrayList<Stock> stockList = stockMapper.selectAllStock();
    ArrayList<Stock> nScheduleList = nSchedule.getItems();
    for (Stock schedule : nScheduleList) {
      // 入荷する商品が倉庫にあるか確認し、あれば追加、なければ挿入する
      for (int i = 0; i < stockList.size(); i++) {
        if (stockList.get(i).getItem_id() == schedule.getItem_id()
            && stockList.get(i).getShop_id() == schedule.getShop_id()) {
          flag = true;
          break;
        }
      }
      if (!flag) {
        stockMapper.insertItem(schedule);
      } else if (flag) {
        Stock stock = new Stock();
        stock = stockMapper.selectById(schedule.getItem_id(), schedule.getShop_id());
        int newNumber = stock.getNumber() + schedule.getNumber();
        stock.setNumber(newNumber);
        stockMapper.updateById(stock);
        flag = false;
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
  public String chat1(ModelMap model, @RequestParam String msg, Principal name) {
    Chat chat = new Chat();
    chat.setName(userMapper.selectUserNameByUserId(name.getName()));
    chat.setMessage(msg);
    ArrayList<Chat> chatlist = asyncChat.addChat(chat);
    model.addAttribute("chats", chatlist);
    return "chat.html";
  }

  @GetMapping("syukka1")
  public String syukka1(ModelMap model, Principal prin) {
    int id = Integer.parseInt(prin.getName());
    User user = userMapper.selectById(id);
    ArrayList<Zaiko> zaikolist = zaikoMapper.selectById(user.getShop_id());
    model.addAttribute("zaikolist", zaikolist);
    ArrayList<Shop> shops = shopMapper.selectAllShop();
    model.addAttribute("shops", shops);
    return "syukka.html";
  }

  @PostMapping("syukka2")
  public String syukka2(ModelMap model, @RequestParam Integer item_id, Principal prin, @RequestParam Integer number,
      @RequestParam Integer recv_shop_id, @RequestParam String msg) {
    int id = Integer.parseInt(prin.getName());
    User user = userMapper.selectById(id);

    String message = "結果なし";

    //出荷先店舗が存在するか確認
    ArrayList<Shop> shoplist = shopMapper.selectAllShop();
    boolean flag = false;
    for (Shop shop : shoplist) {
      if (shop.getShop_id() == recv_shop_id) {
        flag = true;
        break;
      }
    }
    if(!flag){
      message = "エラー:店舗が存在しません";
    }

    Stock item = new Stock();
    item.setItem_id(item_id);
    item.setShop_id(user.getShop_id());
    item.setNumber(number);
    
    ArrayList<Stock> stockList = stockMapper.selectAllStock();
    for (Stock stock : stockList) {
      if (item.getItem_id() == stock.getItem_id() && item.getShop_id() == stock.getShop_id()) {
        if (item.getNumber() <= stock.getNumber()) {
          int tmp = stock.getNumber() - item.getNumber();
          item.setNumber(tmp);
          stockMapper.updateById(item);
          message = "出荷完了しました";
        } else {
          message = "エラー:在庫数より出荷数の方が多いです";
        }
        break;
      } else {
        message = "エラー:商品が在庫に存在しません";
      }
    }

    //ログ生成
    Date date = new Date();
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
    logMapper.insertLog(log);

    ArrayList<Zaiko> zaikolist = zaikoMapper.selectById(user.getShop_id());
    model.addAttribute("zaikolist", zaikolist);
    ArrayList<Shop> shops = shopMapper.selectAllShop();
    model.addAttribute("shops", shops);
    model.addAttribute("result", message);

    return "syukka.html";
  }

  @GetMapping("denpyo")
  public String denpyo(ModelMap model) {
    ArrayList<Log> logs = logMapper.selectAllLogs();
    model.addAttribute("logs", logs);
    return "denpyo.html";
  }
}
