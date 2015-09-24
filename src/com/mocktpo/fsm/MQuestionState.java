package com.mocktpo.fsm;

public interface MQuestionState extends MState {

    void nextOval(MContext ctx);

    void okOval(MContext ctx);
}
