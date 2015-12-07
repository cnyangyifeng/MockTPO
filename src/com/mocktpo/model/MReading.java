package com.mocktpo.model;

import java.util.List;

public class MReading {

    protected String index;
    protected int totalQuestions;
    protected List<MReadingPassage> passages;

    public String getIndex() {
        return this.index;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public List<MReadingPassage> getPassages() {
        return this.passages;
    }
}
