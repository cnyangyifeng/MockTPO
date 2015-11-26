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

    @Override
    public boolean sectionExitButtonEnabled() {
        return true;
    }

    @Override
    public boolean questionNumberPaneEnabled() {
        return false;
    }

    @Override
    public boolean nextButtonEnabled() {
        return true;
    }

    @Override
    public boolean okButtonEnabled() {
        return true;
    }

    @Override
    public boolean helpButtonEnabled() {
        return true;
    }

    @Override
    public boolean volumeButtonEnabled() {
        return true;
    }

    @Override
    public boolean continueButtonEnabled() {
        return true;
    }

    @Override
    public boolean timerLabelEnabled() {
        return true;
    }

    @Override
    public boolean hideOrShowTimerButtonEnabled() {
        return true;
    }
}
