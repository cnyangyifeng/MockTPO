package com.mocktpo.ui.tests.misc;

import com.mocktpo.ui.widgets.BodyPanel;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;

public class GeneralTestInfoPanel extends BodyPanel {

    private JEditorPane descriptionPane;

    public GeneralTestInfoPanel(Rectangle bounds) {
        super(bounds);
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setDescriptionPane();
    }

    protected void setDescriptionPane() {
        this.descriptionPane = new JEditorPane();

        this.descriptionPane.setBounds(0, 0, this.getWidth(), this.getHeight());

        this.descriptionPane.setEditable(false);
        this.descriptionPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".gti-header { color: #3d4167; font-family: Georgia; font-size: 18px; font-weight: bold; text-align: center; margin-top: 60px; margin-bottom: 40px; } .gti { color: #333333; font-family: Arial; font-size: 12px; margin-left: 24px; margin-right: 24px; margin-bottom: 12px; }");
        this.descriptionPane.setEditorKit(kit);
        String text = "<div class='gti-header'>General Test Information</div>";
        text += "<div class='gti'>This test measures your ability to use English in an academic context. There are 4 sections.</div>";
        text += "<div class='gti'>In the <b>Reading</b> section, you will read several passages and answer questions about them.</div>";
        text += "<div class='gti'>In the <b>Listening</b> section, you will hear several conversations and lectures and answer questions about them.</div>";
        text += "<div class='gti'>In the <b>Speaking</b> section, you will answer 6 questions. Some of the questions ask you to speak about your own experience. Other questions ask you to speak about lectures and reading passages.</div>";
        text += "<div class='gti'>In the <b>Writing</b> section, you will answer 2 questions. The first question asks you to write about the relationship between a lecture you will hear and a passage you will read. The second question asks you to write an essay about a topic of general interest based on your experience.</div>";
        text += "<div class='gti'>During this practice test, you may click <b>Pause</b> icon at any time. This will stop the test until you decide to continue. You may continue the test in a few minutes, or at any time during the period that your test is activated.</div>";
        text += "<div class='gti'>There will be directions for each section which explain how to answer the questions in that section.</div>";
        text += "<div class='gti'>You should work quickly but carefully on the Reading and Listening questions. Some questions are more difficult than others, but try to answer every one to the best of your ability. If you are not sure of the answer to a question, make the best guess that you can. The questions that you answer by speaking and by writing are each separately timed. Try to answer every one of these questions as completely as possible in the time allowed.</div>";
        text += "<div class='gti'>Click on <b>Continue</b> to go on.</div>";
        this.descriptionPane.setText(text);

        this.add(this.descriptionPane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        Color bg = new Color(242, 232, 200); // #f2e8c8
        g2d.setPaint(bg);
        g2d.fillRect(0, 0, width, height);
    }
}
