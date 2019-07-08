package com.meeting.bean;

public class MeetingTeam {
    private Integer id;

    private String username;

    private String memberIds;

    private Boolean deleteFlag;

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
        this.username = username == null ? null : username.trim();
    }

    public String getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds == null ? null : memberIds.trim();
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}