package com.mocktpo.model;

import java.util.List;

public class MListening {

    protected String index;
    protected List<MListeningPassage> passages;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<MListeningPassage> getPassages() {
        return passages;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
