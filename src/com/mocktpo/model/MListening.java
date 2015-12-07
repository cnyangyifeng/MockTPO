package com.mocktpo.model;

import java.util.List;

public class MListening {

    protected String index;
    protected List<MListeningPassage> passages;

    public String getIndex() {
        return this.index;
    }

    public List<MListeningPassage> getPassages() {
        return this.passages;
    }
}
