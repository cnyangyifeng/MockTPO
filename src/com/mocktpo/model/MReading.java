package com.mocktpo.model;

import java.util.List;

public class MReading {

    protected String index;
    protected List<MReadingPassage> passages;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<MReadingPassage> getPassages() {
        return this.passages;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
