package com.mocktpo.ui.widgets;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;

        Color start = new Color(47, 82, 140);
        Color end = new Color(135, 33, 52);

        // Color start = new Color(50, 86, 138);
        // Color end = new Color(126, 49, 56);

        // Color start = new Color(38, 66, 119);
        // Color end = new Color(105, 34, 45);

        // Color start = new Color(65, 86, 135);
        // Color end = new Color(110, 52, 61);

        GradientPaint gp = new GradientPaint(0, 0, start, width, height, end);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}
