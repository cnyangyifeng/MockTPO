package com.mocktpo.ui.tests.misc;

import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.util.GlobalConstants;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;

public class CopyrightPanel extends BodyPanel {

    public static final int DESCRIPTION_PANE_WIDTH = 800;
    public static final int DESCRIPTION_PANE_HEIGHT = 400;

    private JEditorPane descriptionPane;

    public CopyrightPanel(Rectangle bounds) {
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
        style.addRule(".cp-img { margin-bottom: 40px; text-align: center; } .cp-img img { height: 90px; width: 160px; } .cp { color: #333333; font-family: Arial; font-size: 12px; text-align: center; }");
        this.descriptionPane.setEditorKit(kit);
        String imgUrl = this.getClass().getResource(GlobalConstants.IMAGES_DIR + "ets_toefl.png").toString();
        String text = "<div class='cp-img'><img src='" + imgUrl + "' /></div>";
        text += "<div class='cp'>Copyright 2009, 2011 by Educational Testing Service. All rights reserved.<br />EDUCATIONAL TESTING SERVICE, ETS, the ETS logo, TOEFL and TOEFL iBT are registered trademarks of<br />Educational Testing Service (ETS) in the United States and other countries.<br /><br />Click on <b>Continue</b> to go on.</div>";
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
