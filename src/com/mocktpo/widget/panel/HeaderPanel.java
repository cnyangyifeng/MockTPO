package com.mocktpo.widget.panel;

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
        GradientPaint gp = new GradientPaint(0, 0, start, width, height, end);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}
