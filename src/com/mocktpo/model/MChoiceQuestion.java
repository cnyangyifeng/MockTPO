package com.mocktpo.model;

import java.util.List;

public class MChoiceQuestion {

    private String index;
    private MAudio audio;
    private String subject;
    private String answer;
    private List<MChoiceOption> options;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public MAudio getAudio() {
        return this.audio;
    }

    public void setAudio(MAudio audio) {
        this.audio = audio;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<MChoiceOption> getOptions() {
        return this.options;
    }

    public void setOptions(List<MChoiceOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
