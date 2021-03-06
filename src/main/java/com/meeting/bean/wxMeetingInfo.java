package com.meeting.bean;

public class wxMeetingInfo {
    String name;
    String addr;
    String date;
    String time;
    Boolean isSignin;

    public wxMeetingInfo() {
    }

    public wxMeetingInfo(String name, String addr, String date, String time, Boolean isSignin) {
        this.name = name;
        this.addr = addr;
        this.date = date;
        this.time = time;
        this.isSignin = isSignin;
    }

    @Override
    public String toString() {
        return "wxMeetingInfo{" +
                "name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", isSignin='" + isSignin + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getIsSignin() {
        return isSignin;
    }

    public void setIsSignin(Boolean isSignin) {
        this.isSignin = isSignin;
    }
}
