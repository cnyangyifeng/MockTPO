package com.mocktpo.model;

import java.util.List;

public class MReadingPassage {

    private String index;
    private String title;
    private List<MReadingParagraph> paragraphs;
    private List<MChoiceQuestion> questions;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MReadingParagraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<MReadingParagraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public List<MChoiceQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<MChoiceQuestion> questions) {
        this.questions = questions;
    }
}
