package com.mocktpo.model;

import java.util.List;

public class MLecture {

    private String index;
    private List<MImage> images;
    private List<MAudio> audios;
    private List<MChoiceQuestion> questions;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    public List<MChoiceQuestion> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<MChoiceQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
