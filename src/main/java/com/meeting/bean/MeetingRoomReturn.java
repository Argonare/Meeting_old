package com.meeting.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeetingRoomReturn {
    private Integer id;

    private String address;

    private String adminName;

    public MeetingRoomReturn() {
    }

    public MeetingRoomReturn(Integer id, String address, String adminName) {
        this.id = id;
        this.address = address;
        this.adminName = adminName;
    }

    @Override
    public String toString() {
        return "MeetingRoomReturn{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", adminName='" + adminName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

}
