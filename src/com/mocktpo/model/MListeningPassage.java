package com.mocktpo.model;

import java.util.List;

public class MListeningPassage {

    protected String index;
    protected String type;
    protected List<MImage> images;
    protected List<MAudio> audios;
    protected List<MListeningQuestion> questions;

    public String getIndex() {
        return this.index;
    }

    public String getType() {
        return this.type;
    }

    public List<MImage> getImages() {
        return this.images;
    }

    public List<MAudio> getAudios() {
        return this.audios;
    }

    public List<MListeningQuestion> getQuestions() {
        return this.questions;
    }
}
