package com.hsk.redislogin.model;

/**
 * 유저의 정보를 담고 있는 클래스.
 *
 * @version     1.0 29 Jun 2018
 * @author      Seokgyu Hwang
 */
public class User {
    private String id;       // user ID
    private String pw;       // user Password
    private String name;     // user Name
    private String email;    // user e-Mail

    public  User() {}

    public User(String id, String pw, String name, String email) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
