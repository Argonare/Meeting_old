package com.meeting.bean;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoReturn {
    private Integer id;

    private String username;

    private String name;

    private String phone;

    private String wechatToken;

    private String departName;

    public UserInfoReturn() {
    }

    public UserInfoReturn(Integer id, String username, String name, String phone, String wechatToken, String departName) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.wechatToken = wechatToken;
        this.departName = departName;
    }

    @Override
    public String toString() {
        return "UserInfoReturn{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", wechatToken='" + wechatToken + '\'' +
                ", departName='" + departName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechatToken() {
        return wechatToken;
    }

    public void setWechatToken(String wechatToken) {
        this.wechatToken = wechatToken;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
}
