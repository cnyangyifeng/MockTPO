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

    /**************************************************
     * Control Buttons Status
     **************************************************/

    @Override
    public boolean sectionExitButtonAvailable() {
        return true;
    }

    @Override
    public boolean questionNumberPaneAvailable() {
        return false;
    }

    @Override
    public boolean nextButtonAvailable() {
        return false;
    }

    @Override
    public boolean okButtonAvailable() {
        return false;
    }

    @Override
    public boolean helpButtonAvailable() {
        return false;
    }

    @Override
    public boolean volumeButtonAvailable() {
        return false;
    }

    @Override
    public boolean continueButtonAvailable() {
        return true;
    }

    @Override
    public boolean timerLabelAvailable() {
        return true;
    }

    @Override
    public boolean hideOrShowTimerButtonAvailable() {
        return true;
    }
}
