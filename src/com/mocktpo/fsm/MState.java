package com.mocktpo.fsm;

import com.mocktpo.ui.widgets.BodyPanel;

public interface MState {

    void next(BodyPanel source, MContext ctx);
}
