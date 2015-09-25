package com.mocktpo.model;

public class MImage {

    protected String index;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
