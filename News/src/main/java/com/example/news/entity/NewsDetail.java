package com.example.news.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/25.
 */

public class NewsDetail implements Serializable {
    String summary;
    String stamp;
    String link;
    String icon;
    String title;
    int nid;

    public NewsDetail(String summary, String stamp, String link, String icon, String title, int nid) {
        this.summary = summary;
        this.stamp = stamp;
        this.link = link;
        this.icon = icon;
        this.title = title;
        this.nid = nid;
    }

    @Override
    public String toString() {
        return "NewsDetail{" +
                "summary='" + summary + '\'' +
                ", stamp='" + stamp + '\'' +
                ", link='" + link + '\'' +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", nid='" + nid + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
