package com.meeting.bean;

public class MeetingInfo {
    private Integer id;

    private String name;

    @Override
    public String toString() {
        return "MeetingInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departIds='" + departIds + '\'' +
                ", type=" + type +
                ", insertUsername='" + insertUsername + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", roomId=" + roomId +
                ", deleteFlag=" + deleteFlag +
                ", lateTime=" + lateTime +
                ", refreshQcode=" + refreshQcode +
                '}';
    }

    private String departIds;

    private Integer type;

    private String insertUsername;

    private Long startTime;

    private Long endTime;

    private Integer roomId;

    private Boolean deleteFlag;

    private Integer lateTime;

    private Boolean refreshQcode;

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

    public String getDepartIds() {
        return departIds;
    }

    public void setDepartIds(String departIds) {
        this.departIds = departIds == null ? null : departIds.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInsertUsername() {
        return insertUsername;
    }

    public void setInsertUsername(String insertUsername) {
        this.insertUsername = insertUsername == null ? null : insertUsername.trim();
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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getLateTime() {
        return lateTime;
    }

    public void setLateTime(Integer lateTime) {
        this.lateTime = lateTime;
    }

    public Boolean getRefreshQcode() {
        return refreshQcode;
    }

    public void setRefreshQcode(Boolean refreshQcode) {
        this.refreshQcode = refreshQcode;
    }
}