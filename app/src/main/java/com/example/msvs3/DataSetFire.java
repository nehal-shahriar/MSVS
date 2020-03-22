package com.example.msvs3;

public class DataSetFire {
    String name;
    String id;
    String dept;
    String cg;
    String department;
    String vote;
    String email;
    String barcodeid,propaganda,imgurl;


    public DataSetFire() {
    }

    public DataSetFire(String name, String id, String dept, String cg, String department, String vote, String email, String barcodeid,String propaganda,String imgurl) {
        this.name = name;
        this.id = id;
        this.dept = dept;
        this.cg = cg;
        this.department = department;
        this.vote = vote;
        this.email = email;
        this.barcodeid = barcodeid;
        this.propaganda=propaganda;
        this.imgurl=imgurl;

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

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBarcodeid() {
        return barcodeid;
    }

    public void setBarcodeid(String barcodeid) {
        this.barcodeid = barcodeid;
    }


    public String getPropaganda() {
        return propaganda;
    }

    public void setPropaganda(String propaganda) {
        this.propaganda = propaganda;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
