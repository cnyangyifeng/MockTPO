package com.mocktpo.ui.widgets;

import java.awt.*;

public class MOvalButton extends MButton {

    public static final int TEXT_WIDTH = 36;

    private String text;
    private boolean enabled;

    public MOvalButton(String text, boolean enabled) {
        super();
        this.text = text;
        this.enabled = enabled;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int w = this.getWidth();
        int h = this.getHeight() - 10;

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHints(hints);

        Color bg;
        if (enabled) {
            bg = new Color(75, 95, 143);
        } else {
            bg = new Color(102, 102, 102);
        }
        g2d.setPaint(bg);
        g2d.translate(0, 5);
        double theta = Math.toRadians(-20);
        g2d.rotate(theta, w / 2, h / 2);
        g2d.drawOval(0, 0, w, h);
        g2d.fillOval(0, 0, w, h);
        double back = Math.toRadians(20);
        g2d.rotate(back, w / 2, h / 2);

        Color c = new Color(255, 255, 255);
        g2d.setPaint(c);
        g2d.setFont(new Font("Arial", Font.PLAIN, 11));
        int x = (this.getWidth() - TEXT_WIDTH) / 2;
        g2d.drawString(this.text, x, h / 2);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.enabled = enabled;
        this.repaint();
    }
}
