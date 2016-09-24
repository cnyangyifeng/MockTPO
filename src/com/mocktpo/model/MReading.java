package com.mocktpo.model;

import java.util.List;

public class MReading {

    private String index;
    private int totalQuestions;
    private List<MReadingPassage> passages;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public List<MReadingPassage> getPassages() {
        return passages;
    }

    public void setPassages(List<MReadingPassage> passages) {
        this.passages = passages;
    }
}
