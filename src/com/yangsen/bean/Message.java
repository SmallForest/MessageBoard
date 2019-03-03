package com.yangsen.bean;

import java.util.Date;

/**
 * message bean
 */
public class Message {
    private int id;

    private int userId;//等同于user_id

    private String username;

    private String title;

    private String content;

    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Message(int id, int userId, String username, String title, String content, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }
    public Message(int userId, String username, String title, String content) {
        this.userId = userId;
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public Message(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Message() {
    }
}

