package com.meeting.bean;

public class MeetingInfoRetrun {
    private Integer id;
    private String name;
    private Long startTime;
    private Long endTime;
    private String deptName;//部门名称
    private String address;//会议地点
    private boolean qcode;
    private int meetingType;
    private int lateTime;

    @Override
    public String toString() {
        return "MeetingInfoRetrun{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", deptName='" + deptName + '\'' +
                ", address='" + address + '\'' +
                ", qcode=" + qcode +
                ", meetingType=" + meetingType +
                ", lateTime=" + lateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isQcode() {
        return qcode;
    }

    public void setQcode(boolean qcode) {
        this.qcode = qcode;
    }

    public int getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(int meetingType) {
        this.meetingType = meetingType;
    }

    public int getLateTime() {
        return lateTime;
    }

    public void setLateTime(int lateTime) {
        this.lateTime = lateTime;
    }
}
