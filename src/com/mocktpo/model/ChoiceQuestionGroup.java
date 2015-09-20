package com.mocktpo.model;

import java.util.List;

public class ChoiceQuestionGroup {

    private String index;
    private List<ChoiceQuestion> questions;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<ChoiceQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ChoiceQuestion> questions) {
        this.questions = questions;
    }
}
