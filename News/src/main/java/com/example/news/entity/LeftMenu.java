package com.example.news.entity;

/**
 * Created by Administrator on 2016/12/25.
 */

public class LeftMenu {
    String name;
    String english;
    int icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public LeftMenu(String name, String english, int icon) {
        this.name = name;
        this.english = english;
        this.icon = icon;

    }
}
