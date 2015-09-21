package com.mocktpo.model;

public class MTest {

    private String index;
    private String name;
    private String status;

    public MTest(String index, String name) {
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

    @Override
    public String toString() {
        return this.index;
    }
}
