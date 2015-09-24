package com.mocktpo.fsm;

import com.mocktpo.ui.widgets.BodyPanel;

public class MContext {

    private MState state;

    public MContext(MState initial) {
        this.setState(initial);
    }

    public MState getState() {
        return state;
    }

    public void setState(MState state) {
        this.state = state;
    }

    public void next(BodyPanel source) {
        this.state.next(source, this);
    }
}
