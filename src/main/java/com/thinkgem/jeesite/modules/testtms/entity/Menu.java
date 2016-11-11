/**
  * Copyright 2016 bejson.com 
  */
package com.thinkgem.jeesite.modules.testtms.entity;
import java.util.List;


 
public class Menu {

    private int id;
    private String text;
    private List<Items> items;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setText(String text) {
         this.text = text;
     }
     public String getText() {
         return text;
     }

    public void setItems(List<Items> items) {
         this.items = items;
     }
     public List<Items> getItems() {
         return items;
     }
}