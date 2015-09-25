package com.mocktpo.model;

public class MChoiceOption {

    protected String index;
    protected String text;

    public MChoiceOption(String index, String text) {
        this.index = index;
        this.text = text;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
