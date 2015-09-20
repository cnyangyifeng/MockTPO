package com.mocktpo.model;

public class ChoiceOption {

    private String index;
    private String text;

    public ChoiceOption(String index, String text) {
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
}
