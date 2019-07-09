package com.meeting.bean;

public class DepartmentReturn {
    private Integer id;
    private String name;

    public DepartmentReturn() {
    }

    public DepartmentReturn(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DepartmentReturn{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
}
