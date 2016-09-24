package com.mocktpo.model;

public class MChoiceOption {

    private String index;
    private String text;

    public MChoiceOption(String index, String text) {
        this.index = index;
        this.text = text;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
