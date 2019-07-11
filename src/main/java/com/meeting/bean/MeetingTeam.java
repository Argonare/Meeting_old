package com.meeting.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeetingTeam {
    private Integer id;

    private String name;

    private String username;

    private String memberIds;

    private Boolean deleteFlag;

    public MeetingTeam() {
    }

    public MeetingTeam(Integer id, String name, String username, String memberIds, Boolean deleteFlag) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.memberIds = memberIds;
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "MeetingTeam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", memberIds='" + memberIds + '\'' +
                ", deleteFlag=" + deleteFlag +
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
        this.name = name == null ? null : name.trim();
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