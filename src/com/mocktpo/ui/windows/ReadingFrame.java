package com.mocktpo.ui.windows;

import com.mocktpo.ui.tests.reading.ReadingDirectionsPanel;
import com.mocktpo.ui.tests.reading.ReadingPassagePanel;

import java.awt.*;

public class ReadingFrame extends TestWithSectionControlFrame {

    // Components

    private ReadingDirectionsPanel rdPanel;
    private ReadingPassagePanel rpPanel;

    public ReadingFrame(GraphicsConfiguration gc, MainFrame mainFrame) {
        super(gc, mainFrame);
    }

    @Override
    protected void configData() {
    }

    @Override
    protected void customizeBodyPanel() {
        this.rdPanel = new ReadingDirectionsPanel(this.bodyBounds);
        this.bodyPanel = this.rdPanel;
        // this.rpPanel = new ReadingPassagePanel(this.bodyBounds);
        // this.bodyPanel = this.rpPanel;
    }
}
