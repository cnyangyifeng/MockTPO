package com.mocktpo.model;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.net.URL;
import java.util.List;

public class ChoiceQuestion {

    private String index;
    private String audio;
    private String subject;
    private String answer;
    private List<ChoiceOption> options;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAudio() {
        return this.audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
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

    public List<ChoiceOption> getOptions() {
        return options;
    }

    public void setOptions(List<ChoiceOption> options) {
        this.options = options;
    }

    public static void main(String[] args) {
        XStream xs = new XStream();
        xs.alias("question", ChoiceQuestion.class);
        xs.alias("option", ChoiceOption.class);

        URL xml = ChoiceQuestion.class.getResource("/resources/tests/TPO25/listening/L1-C1-Q.xml");
        try {
            ChoiceQuestion cq = (ChoiceQuestion) xs.fromXML(new File(xml.toURI()));
            System.out.println(cq.getSubject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
