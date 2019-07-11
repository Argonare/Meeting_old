package com.meeting.bean;

public class Department {
    private Integer id;

    private String name;

    private Boolean deleteFlag;

    public Department() {
    }

    public Department(Integer id, String name, Boolean deleteFlag) {
        this.id = id;
        this.name = name;
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
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

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}