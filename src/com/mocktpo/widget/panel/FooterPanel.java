package com.mocktpo.widget.panel;

import javax.swing.*;
import java.awt.*;

public class FooterPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        Color bg = new Color(60, 77, 130); // #3c4d82
        g2d.setPaint(bg);
        g2d.fillRect(0, 0, width, height);
    }
}
