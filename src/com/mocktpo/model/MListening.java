package com.mocktpo.model;

import java.util.List;

public class MListening {

    private String index;
    private List<MConversation> conversations;
    private List<MLecture> lectures;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<MConversation> getConversations() {
        return this.conversations;
    }

    public void setConversations(List<MConversation> conversations) {
        this.conversations = conversations;
    }

    public List<MLecture> getLectures() {
        return this.lectures;
    }

    public void setLectures(List<MLecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
