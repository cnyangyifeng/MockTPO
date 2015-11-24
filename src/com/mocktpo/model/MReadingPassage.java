package com.mocktpo.model;

import java.util.List;

public class MReadingPassage {

    protected String index;
    protected String title;
    protected List<MReadingParagraph> paragraphs;
    protected List<MChoiceQuestion> questions;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTitle() {
        return this.title;
    }

    public List<MReadingParagraph> getParagraphs() {
        return this.paragraphs;
    }

    public List<MChoiceQuestion> getQuestions() {
        return this.questions;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
