package com.meeting.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeetingTeamReturn {
    private Integer id;
    private String name;
    private String username;
    private String memberIds;

    public MeetingTeamReturn() {
    }

    public MeetingTeamReturn(Integer id, String name, String username, String memberIds) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.memberIds = memberIds;
    }

    @Override
    public String toString() {
        return "MeetingTeamReturn{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", memberIds='" + memberIds + '\'' +
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds;
    }
}
