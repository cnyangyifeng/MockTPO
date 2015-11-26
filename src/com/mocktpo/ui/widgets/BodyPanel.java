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

    public boolean sectionExitButtonEnabled() {
        return false;
    }

    public boolean questionNumberPaneEnabled() {
        return false;
    }

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

    public boolean continueButtonEnabled() {
        return true;
    }

    public boolean timerLabelEnabled() {
        return false;
    }

    public boolean hideOrShowTimerButtonEnabled() {
        return false;
    }
}
