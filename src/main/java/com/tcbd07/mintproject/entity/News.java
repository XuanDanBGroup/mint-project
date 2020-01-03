package com.tcbd07.mintproject.entity;

import java.io.Serializable;
import java.sql.Date;

public class News implements Serializable {
    private Integer news_id,news_type,news_level,news_index,news_status;
    private String news_title,news_content,news_owner;
    private Date news_createdTime,news_verifiedTime,news_updatedTime;

    @Override
    public String toString() {
        return "News{" +
                "news_id=" + news_id +
                ", news_type=" + news_type +
                ", news_level=" + news_level +
                ", news_index=" + news_index +
                ", news_status=" + news_status +
                ", news_title='" + news_title + '\'' +
                ", news_content='" + news_content + '\'' +
                ", news_owner='" + news_owner + '\'' +
                ", news_createdTime=" + news_createdTime +
                ", news_verifiedTime=" + news_verifiedTime +
                ", news_updatedTime=" + news_updatedTime +
                '}';
    }

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }

    public Integer getNews_type() {
        return news_type;
    }

    public void setNews_type(Integer news_type) {
        this.news_type = news_type;
    }

    public Integer getNews_level() {
        return news_level;
    }

    public void setNews_level(Integer news_level) {
        this.news_level = news_level;
    }

    public Integer getNews_index() {
        return news_index;
    }

    public void setNews_index(Integer news_index) {
        this.news_index = news_index;
    }

    public Integer getNews_status() {
        return news_status;
    }

    public void setNews_status(Integer news_status) {
        this.news_status = news_status;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_content() {
        return news_content;
    }

    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }

    public String getNews_owner() {
        return news_owner;
    }

    public void setNews_owner(String news_owner) {
        this.news_owner = news_owner;
    }

    public Date getNews_createdTime() {
        return news_createdTime;
    }

    public void setNews_createdTime(Date news_createdTime) {
        this.news_createdTime = news_createdTime;
    }

    public Date getNews_verifiedTime() {
        return news_verifiedTime;
    }

    public void setNews_verifiedTime(Date news_verifiedTime) {
        this.news_verifiedTime = news_verifiedTime;
    }

    public Date getNews_updatedTime() {
        return news_updatedTime;
    }

    public void setNews_updatedTime(Date news_updatedTime) {
        this.news_updatedTime = news_updatedTime;
    }
}
