package com.mocktpo.widget.panel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImagePanel extends JPanel {

    private Image image;

    public ImagePanel(URL url) {
        this.image = Toolkit.getDefaultToolkit().getImage(url);
        this.setBackground(new Color(51, 51, 51));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
