package com.mocktpo.ui.tests.reading;

import com.mocktpo.model.MReadingPassage;
import com.mocktpo.ui.widgets.BodyPanel;

import java.awt.*;

public class ReadingPassagePanel extends BodyPanel {

    private MReadingPassage passage;

    public ReadingPassagePanel(Rectangle bounds, MReadingPassage passage) {
        super(bounds);
        this.passage = passage;
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
    }


}
