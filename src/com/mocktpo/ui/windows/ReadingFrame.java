package com.mocktpo.ui.windows;

import com.mocktpo.ui.tests.reading.ReadingDirectionsPanel;

import java.awt.*;

public class ReadingFrame extends AbstractTestSectionFrame {

    // Components

    private ReadingDirectionsPanel rdPanel;

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
    }
}
