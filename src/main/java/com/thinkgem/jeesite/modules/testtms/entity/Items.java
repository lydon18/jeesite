package com.thinkgem.jeesite.modules.testtms.entity;

public class Items {

   private int id;
   private String text;
   private String uri;
   private String icon;
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

   public void setUri(String uri) {
        this.uri = uri;
    }
    public String getUri() {
        return uri;
    }

   public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }

}