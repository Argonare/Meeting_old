package com.meeting.bean;

public class MeetingSignin {
    private Integer id;

    private Integer userId;

    private Integer meetingId;

    private Long signinTime;

    private Boolean signinFlag;

    private Boolean leaveFlag;

    private Boolean lateFlag;

    private Boolean deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Long getSigninTime() {
        return signinTime;
    }

    public void setSigninTime(Long signinTime) {
        this.signinTime = signinTime;
    }

    public Boolean getSigninFlag() {
        return signinFlag;
    }

    public void setSigninFlag(Boolean signinFlag) {
        this.signinFlag = signinFlag;
    }

    public Boolean getLeaveFlag() {
        return leaveFlag;
    }

    public void setLeaveFlag(Boolean leaveFlag) {
        this.leaveFlag = leaveFlag;
    }

    public Boolean getLateFlag() {
        return lateFlag;
    }

    public void setLateFlag(Boolean lateFlag) {
        this.lateFlag = lateFlag;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}