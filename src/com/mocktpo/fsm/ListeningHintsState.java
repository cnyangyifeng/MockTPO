package com.mocktpo.fsm;

import com.mocktpo.ui.widgets.BodyPanel;

public class ListeningHintsState implements MState {

    public void next(BodyPanel source, MContext ctx) {
        ctx.setState(new ChoiceQuestionState());
    }
}
