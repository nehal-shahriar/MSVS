package com.example.msvs3;

public class DataSetFire {
    String name;
    String id;
    String dept;

    public DataSetFire() {
    }

    public DataSetFire(String name, String id, String dept) {
        this.name = name;
        this.id = id;
        this.dept = dept;
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
}
