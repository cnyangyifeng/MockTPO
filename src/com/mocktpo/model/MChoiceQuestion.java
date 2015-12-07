package com.mocktpo.model;

import java.util.List;

public class MChoiceQuestion {

    protected int number;
    protected String subject;
    protected String answer;
    protected List<MChoiceOption> options;

    public int getNumber() {
        return this.number;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getAnswer() {
        return this.answer;
    }

    public List<MChoiceOption> getOptions() {
        return this.options;
    }
}
