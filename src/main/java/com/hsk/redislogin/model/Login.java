package com.hsk.redislogin.model;

/**
 * 로그인 버튼 클릭으로 넘어온 id, pw를 갖고 있는 클래스
 *
 * @version     1.0 29 Jun 2018
 * @author      Seokgyu Hwang
 */
public class Login {
    private String id;    // Given ID
    private String pw;    // Given Password

    public Login(String id, String pw) {
        this.id = id;
        this.pw = pw;
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
}
