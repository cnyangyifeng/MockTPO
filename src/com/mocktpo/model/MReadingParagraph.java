package com.mocktpo.model;

public class MReadingParagraph {

    protected String index;
    protected String text;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
