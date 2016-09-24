package com.mocktpo.model;

import java.util.List;

public class MChoiceQuestion {

    private int number;
    private String subject;
    private String answer;
    private List<MChoiceOption> options;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<MChoiceOption> getOptions() {
        return options;
    }

    public void setOptions(List<MChoiceOption> options) {
        this.options = options;
    }
}
