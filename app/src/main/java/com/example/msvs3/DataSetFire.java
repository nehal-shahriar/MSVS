package com.example.msvs3;

public class DataSetFire {
    String name;
    String id;
    String dept;
    String cg;
    String department;
    String vote;

    public DataSetFire() {
    }

    public DataSetFire(String name, String id, String dept, String cg,String department,String vote) {
        this.name = name;
        this.id = id;
        this.dept = dept;
        this.cg=cg;
        this.department=department;
        this.vote=vote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCg() {
        return cg;
    }

    public void setCg(String cg) {
        this.cg = cg;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getVote() {
        return vote;
    }

    public void setVoteno(String voteno) {
        this.vote = vote;
    }
}
