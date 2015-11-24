package com.mocktpo.model;

import java.util.List;

public class MReadingPassage {

    protected String index;
    protected String text;
    protected List<MChoiceQuestion> questions;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getText() {
        return this.text;
    }

    public List<MChoiceQuestion> getQuestions() {
        return this.questions;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
