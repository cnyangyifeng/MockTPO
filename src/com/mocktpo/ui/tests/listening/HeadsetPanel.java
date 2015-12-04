package com.mocktpo.ui.tests.listening;

import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.util.GlobalConstants;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;

public class HeadsetPanel extends BodyPanel {

    public static final int DESCRIPTION_PANE_WIDTH = 600;
    public static final int DESCRIPTION_PANE_HEIGHT = 480;

    private JEditorPane descriptionPane;

    public HeadsetPanel(Rectangle bounds) {
        super(bounds);
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setDescriptionPane();
    }

    protected void setDescriptionPane() {
        this.descriptionPane = new JEditorPane();

        int x = (this.getWidth() - DESCRIPTION_PANE_WIDTH) / 2;
        int y = (this.getHeight() - DESCRIPTION_PANE_HEIGHT) / 2;
        this.descriptionPane.setBounds(x, y, DESCRIPTION_PANE_WIDTH, DESCRIPTION_PANE_HEIGHT);

        this.descriptionPane.setEditable(false);
        this.descriptionPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".hs-header { color: #333333; font-family: Arial; font-size: 12px; margin-bottom: 20px; text-align: center; } .hs-body { margin-bottom: 20px; text-align: center; } .hs-footer { color: #333333; font-family: Arial; font-size: 12px; text-align: center; }");
        this.descriptionPane.setEditorKit(kit);
        String text = "<div class='hs-header'>Now put on your headset.</div>";
        String imgUrl = this.getClass().getResource(GlobalConstants.IMAGES_DIR + "headset.png").toString();
        text += "<div class='hs-body'><img src='" + imgUrl + "' /></div>";
        text += "<div class='hs-footer'>Click on <b>Continue</b> to go on.</div>";
        this.descriptionPane.setText(text);

        this.add(this.descriptionPane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        Color bg = new Color(238, 213, 204);
        g2d.setPaint(bg);
        g2d.fillRect(0, 0, width, height);
    }
}
