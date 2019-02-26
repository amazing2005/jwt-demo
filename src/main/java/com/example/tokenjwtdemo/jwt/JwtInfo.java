package com.example.tokenjwtdemo.jwt;

public class JwtInfo {

    private Integer userId;

    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public JwtInfo(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String
    toString() {
        return "JwtInfo{" +
                "userId=" + userId +
                '}';
    }
}
