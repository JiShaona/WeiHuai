package com.example.shaonaji.weihuai;

/**
 * Created by Shaona Ji on 2016/3/15.
 */
public class User {
    private String name;
    private String account;
    private String password;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String account, String password, String location) {
        this.name = name;
        this.account = account;
        this.password = password;
        this.location = location;
    }
}
