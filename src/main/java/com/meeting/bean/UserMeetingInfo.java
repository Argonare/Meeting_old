package com.meeting.bean;
/***
 * 获取扫码用户的会议信息
 * ***/
public class UserMeetingInfo {
    protected String meetingName;
    protected String department;
    protected Long startTime;
    protected String room;

    @Override
    public String toString() {
        return "UserMeetingInfo{" +
                "meetingName='" + meetingName + '\'' +
                ", department='" + department + '\'' +
                ", startTime=" + startTime +
                ", room=" + room +
                '}';
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
