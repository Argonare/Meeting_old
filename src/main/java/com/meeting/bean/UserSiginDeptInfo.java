package com.meeting.bean;

public class UserSiginDeptInfo {
    private String Dept;
    private String leave;
    private String signin;
    private String notSigin;
    private String late;

    @Override
    public String toString() {
        return "UserSiginDeptInfo{" +
                "Dept='" + Dept + '\'' +
                ", leave='" + leave + '\'' +
                ", signin='" + signin + '\'' +
                ", notSigin='" + notSigin + '\'' +
                ", late='" + late + '\'' +
                '}';
    }

    public String getDept() {
        return Dept;
    }

    public void setDept(String dept) {
        Dept = dept;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getSignin() {
        return signin;
    }

    public void setSignin(String signin) {
        this.signin = signin;
    }

    public String getNotSigin() {
        return notSigin;
    }

    public void setNotSigin(String notSigin) {
        this.notSigin = notSigin;
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }
}
