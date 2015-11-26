package com.mocktpo.ui.tests.listening;

import com.mocktpo.ui.widgets.BodyPanel;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;

public class ChangingVolumePanel extends BodyPanel {

    public static final int DESCRIPTION_PANE_WIDTH = 680;
    public static final int DESCRIPTION_PANE_HEIGHT = 600;

    private JEditorPane descriptionPane;

    public ChangingVolumePanel(Rectangle bounds) {
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
        this.descriptionPane.setBounds(x, 0, DESCRIPTION_PANE_WIDTH, DESCRIPTION_PANE_HEIGHT);

        this.descriptionPane.setEditable(false);
        this.descriptionPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".cv-header { color: #3d4167; font-family: Georgia; font-size: 18px; font-weight: bold; text-align: center; margin-top: 60px; margin-bottom: 40px; } .cv { color: #333333; font-family: Arial; font-size: 12px; font-weight: normal; margin-bottom: 12px; } .cv-footer { border: 3px solid #3d4167; color: #3d4167; font-family: Arial; font-size: 14px; font-style: italic; font-weight: normal; height: 90px; margin-top: 30px; padding: 30px; text-align: center; }");
        this.descriptionPane.setEditorKit(kit);
        String text = "<div class='cv-header'>Changing the Volume</div>";
        text += "<div class='cv'>To change the volume, click on the <b>Volume</b> icon at the top of the screen. The volume control will appear. Move the volume indicator to the left or to the right to change the volume.</div>";
        text += "<div class='cv'>To close the volume control, click on the Volume icon again.</div>";
        text += "<div class='cv'>You will be able to change the volume during the test if you need to.</div>";
        text += "<div class='cv-footer'>You may now change the volume.<br />When you are finished, click on Continue.</div>";
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

    /**************************************************
     * Control Buttons Status - Available
     **************************************************/

    @Override
    public boolean sectionExitButtonAvailable() {
        return true;
    }

    @Override
    public boolean questionNumberPaneAvailable() {
        return false;
    }

    @Override
    public boolean nextButtonAvailable() {
        return false;
    }

    @Override
    public boolean okButtonAvailable() {
        return false;
    }

    @Override
    public boolean helpButtonAvailable() {
        return false;
    }

    @Override
    public boolean volumeButtonAvailable() {
        return true;
    }

    @Override
    public boolean continueButtonAvailable() {
        return true;
    }

    @Override
    public boolean timerLabelAvailable() {
        return false;
    }

    @Override
    public boolean hideOrShowTimerButtonAvailable() {
        return false;
    }

    /**************************************************
     * Control Buttons Status - Enabled
     **************************************************/

    public boolean volumeButtonEnabled() {
        return true;
    }
}
