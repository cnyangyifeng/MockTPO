package com.mocktpo.ui.widgets;

import javax.swing.*;
import java.awt.*;

public class PauseDialogBodyPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        Color start = new Color(70, 66, 146);
        Color end = new Color(212, 211, 231);
        GradientPaint gp = new GradientPaint(0, 0, start, 0, height, end);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}
