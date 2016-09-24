package com.mocktpo.model;

import java.util.List;

public class MListening {

    private String index;
    private List<MListeningPassage> passages;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<MListeningPassage> getPassages() {
        return passages;
    }

    public void setPassages(List<MListeningPassage> passages) {
        this.passages = passages;
    }
}
