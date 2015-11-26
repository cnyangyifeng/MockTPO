package com.mocktpo.ui.tests.listening;

import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.util.LayoutConstants;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;

public class ListeningHintsPanel extends BodyPanel {

    public static final int DESCRIPTION_PANE_WIDTH = 500;
    public static final int DESCRIPTION_PANE_HEIGHT = 380;

    private JEditorPane descriptionPane;
    private String hints;

    public ListeningHintsPanel(Rectangle bounds, String hints) {
        super(bounds);
        this.hints = hints;
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        this.setDescriptionPane();
    }

    protected void setDescriptionPane() {
        this.descriptionPane = new JEditorPane();

        int x = (this.getWidth() - DESCRIPTION_PANE_WIDTH) / 2;
        int y = LayoutConstants.MARGIN * 5;
        this.descriptionPane.setBounds(x, y, DESCRIPTION_PANE_WIDTH, DESCRIPTION_PANE_HEIGHT);

        this.descriptionPane.setEditable(false);
        this.descriptionPane.setOpaque(true);
        this.descriptionPane.setBackground(new Color(255, 252, 239));

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".hints { color: #333333; font-family: Arial; font-size: 16px; margin-top: 80px; padding: 30px; text-align: center; }");
        this.descriptionPane.setEditorKit(kit);
        String text = "<div class='hints'>" + this.hints + "</div>";
        this.descriptionPane.setText(text);

        this.add(this.descriptionPane);
    }

    /**************************************************
     * Control Buttons Status
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
        return true;
    }

    @Override
    public boolean okButtonAvailable() {
        return true;
    }

    @Override
    public boolean helpButtonAvailable() {
        return true;
    }

    @Override
    public boolean volumeButtonAvailable() {
        return true;
    }

    @Override
    public boolean continueButtonAvailable() {
        return false;
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

    @Override
    public boolean nextButtonEnabled() {
        return false;
    }

    @Override
    public boolean okButtonEnabled() {
        return false;
    }

    @Override
    public boolean helpButtonEnabled() {
        return false;
    }

    @Override
    public boolean volumeButtonEnabled() {
        return true;
    }
}
