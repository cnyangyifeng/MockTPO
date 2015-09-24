package com.mocktpo.model;

import java.util.List;

public class MListening {

    private String index;
    private List<MListeningSection> sections;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<MListeningSection> getSections() {
        return sections;
    }

    public void setSections(List<MListeningSection> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
