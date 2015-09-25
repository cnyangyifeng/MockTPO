package com.mocktpo.model;

import java.util.List;

public class MListeningSection {

    protected String index;
    protected String type;
    protected List<MImage> images;
    protected List<MAudio> audios;
    protected List<MListeningQuestion> questions;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MImage> getImages() {
        return this.images;
    }

    public void setImages(List<MImage> images) {
        this.images = images;
    }

    public List<MAudio> getAudios() {
        return this.audios;
    }

    public void setAudios(List<MAudio> audios) {
        this.audios = audios;
    }

    public List<MListeningQuestion> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<MListeningQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
