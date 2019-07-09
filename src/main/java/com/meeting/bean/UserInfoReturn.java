package com.meeting.bean;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoReturn {
    private Integer id;

    private String username;

    private String name;

    private String phone;

    private String wechar;

    private String departName;

    public UserInfoReturn() {
    }

    public UserInfoReturn(Integer id, String username, String name, String phone, String wechar, String departName) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.wechar = wechar;
        this.departName = departName;
    }

    @Override
    public String toString() {
        return "UserInfoReturn{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", wechar='" + wechar + '\'' +
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

    public String getWechar() {
        return wechar;
    }

    public void setWechar(String wechar) {
        this.wechar = wechar;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
}
