package com.yangsen.bean;

import java.util.Date;

public class User {
    private int    id;
    private String username;
    private String password;
    private String realName;
    private String birthday;
    private String phone;
    private String address;

    public User() {
    }

    public User(int id, String username, String password, String realname, String birthday, String phone, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realname;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realName;
    }

    public void setRealname(String realname) {
        this.realName = realname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
