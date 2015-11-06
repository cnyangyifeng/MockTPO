package com.mocktpo.model;

import java.util.List;

public class MReading {

    protected String index;
    protected List<MReadingSection> sections;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<MReadingSection> getSections() {
        return sections;
    }

    public void setSections(List<MReadingSection> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
