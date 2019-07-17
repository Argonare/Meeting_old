package com.meeting.bean;

public class UserSiginDeptInfo {
    private String dept;
    private int leave;
    private int signin;
    private int notsignin;
    private int late;

    @Override
    public String toString() {
        return "UserSiginDeptInfo{" +
                "dept='" + dept + '\'' +
                ", leave=" + leave +
                ", signin=" + signin +
                ", notsignin=" + notsignin +
                ", late=" + late +
                '}';
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    public int getSignin() {
        return signin;
    }

    public void setSignin(int signin) {
        this.signin = signin;
    }

    public int getNotsignin() {
        return notsignin;
    }

    public void setNotsignin(int notsignin) {
        this.notsignin = notsignin;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }
}
