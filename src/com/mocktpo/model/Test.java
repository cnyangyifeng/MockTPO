package com.mocktpo.model;

public class Test {

    private String index;
    private String name;
    private String status;

    public Test(String index, String name) {
        this.index = index;
        this.name = name;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
