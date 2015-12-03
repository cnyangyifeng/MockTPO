package com.mocktpo.ui.widgets;

import javax.swing.*;
import java.awt.*;

public class BodyPanel extends JPanel {

    public BodyPanel(Rectangle bounds) {
        super();
        this.setBounds(bounds);
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        Color bg = new Color(255, 255, 255); // #ffffff
        g2d.setPaint(bg);
        g2d.fillRect(0, 0, width, height);
    }

    /**************************************************
     * Control Buttons Status - Available
     **************************************************/

    public boolean sectionExitButtonAvailable() {
        return false;
    }

    public boolean questionNumberPaneAvailable() {
        return false;
    }

    public boolean nextButtonAvailable() {
        return false;
    }

    public boolean backButtonAvailable() {
        return false;
    }

    public boolean okButtonAvailable() {
        return false;
    }

    public boolean helpButtonAvailable() {
        return false;
    }

    public boolean reviewButtonAvailable() {
        return false;
    }

    public boolean volumeButtonAvailable() {
        return false;
    }

    public boolean continueButtonAvailable() {
        return true;
    }

    public boolean timerLabelAvailable() {
        return false;
    }

    public boolean hideOrShowTimerButtonAvailable() {
        return false;
    }

    /**************************************************
     * Control Buttons Status - Enabled
     **************************************************/

    public boolean nextButtonEnabled() {
        return false;
    }

    public boolean okButtonEnabled() {
        return false;
    }

    public boolean helpButtonEnabled() {
        return false;
    }

    public boolean volumeButtonEnabled() {
        return false;
    }
}
