package com.mocktpo.fsm;

import com.mocktpo.ui.widgets.BodyPanel;

public class HeadsetState implements MState {

    public void next(BodyPanel source, MContext ctx) {
        ctx.setState(new ChangingVolumeState());
    }
}
