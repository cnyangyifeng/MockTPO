package com.mocktpo.model;

import java.util.List;

public class MListeningPassage {

    private String index;
    private String type;
    private List<MImage> images;
    private List<MAudio> audios;
    private List<MListeningQuestion> questions;

    public String getIndex() {
        return index;
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
        return images;
    }

    public void setImages(List<MImage> images) {
        this.images = images;
    }

    public List<MAudio> getAudios() {
        return audios;
    }

    public void setAudios(List<MAudio> audios) {
        this.audios = audios;
    }

    public List<MListeningQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<MListeningQuestion> questions) {
        this.questions = questions;
    }
}
