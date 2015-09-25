package com.mocktpo.model;

public class MListeningQuestion extends MChoiceQuestion {

    protected MAudio audio;

    public MAudio getAudio() {
        return this.audio;
    }

    public void setAudio(MAudio audio) {
        this.audio = audio;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
