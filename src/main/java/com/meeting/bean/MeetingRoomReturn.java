package com.meeting.bean;

public class MeetingRoomReturn {
    private Integer id;

    private String address;

    private String adminName;

    private String adminPhone;

    @Override
    public String toString() {
        return "MeetingRoomReturn{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
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

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public MeetingRoomReturn() {
    }

    public MeetingRoomReturn(Integer id, String address, String adminName, String adminPhone) {
        this.id = id;
        this.address = address;
        this.adminName = adminName;
        this.adminPhone = adminPhone;
    }
}
