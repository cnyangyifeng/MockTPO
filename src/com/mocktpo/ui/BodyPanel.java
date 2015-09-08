package com.mocktpo.ui;

import javax.swing.*;
import java.awt.*;

public class BodyPanel extends JPanel {

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
}
