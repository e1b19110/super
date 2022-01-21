package com.example.demo.model;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class nSchedule {
  ArrayList<Stock> items = new ArrayList<>();

  public void addItems(Stock item) {
    this.items.add(item);
  }

  public void resetItems() {
    items.clear();
  }

  public ArrayList<Stock> getItems() {
    return items;
  }

  public void setItems(ArrayList<Stock> items) {
    this.items = items;
  }

  public boolean emptyItems() {
    if (items.size() == 0) {
      return true;
    }
    return false;
  }

}
