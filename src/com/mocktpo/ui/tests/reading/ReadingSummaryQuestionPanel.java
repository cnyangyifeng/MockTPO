package com.mocktpo.ui.tests.reading;

import com.mocktpo.model.MReadingPassage;
import com.mocktpo.ui.widgets.BodyPanel;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;

public class ReadingSummaryQuestionPanel extends BodyPanel {

    public static final int DESCRIPTION_PANE_HEIGHT = 160;

    protected JEditorPane descriptionPane;

    private MReadingPassage passage;

    public ReadingSummaryQuestionPanel(Rectangle bounds, MReadingPassage passage) {
        super(bounds);
        this.passage = passage;
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setDescriptionPane();
    }

    @Override
    protected void initButtonStatus() {
        this.setQuestionNumberPaneAvailable(true);
        this.setNextButtonAvailable(true);
        this.setBackButtonAvailable(true);
        this.setHelpButtonAvailable(true);
        this.setReviewButtonAvailable(true);
        this.setNextButtonEnabled(true);
        this.setBackButtonEnabled(true);
        this.setHelpButtonEnabled(true);
        this.setReviewButtonEnabled(true);
        this.setTimerLabelAvailable(true);
        this.setHideOrShowTimerButtonAvailable(true);
    }

    protected void setDescriptionPane() {
        this.descriptionPane = new JEditorPane();

        this.descriptionPane.setBounds(0, 0, this.getWidth(), DESCRIPTION_PANE_HEIGHT);

        this.descriptionPane.setEditable(false);
        this.descriptionPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".rsq { color: #333333; font-family: Arial; font-size: 12px; margin-left: 20px; margin-top: 20px; margin-right: 20px; } .rsq-gray { background-color: #cccccc; color: #333333; font-family: Arial; font-size: 12px; margin-left: 20px; margin-top: 20px; margin-right: 20px; padding: 5px; text-align: center; }");
        this.descriptionPane.setEditorKit(kit);
        String text = "<div class='rsq'><b>Directions</b>: An introductory sentence for a brief summary of the passage is provided below. Complete the summary by selecting the THREE answer choices that express the most important ideas in the passage. Some sentences do not belong in the summary because they express ideas that are not presented in the passage or are minor ideas in the passage. <b>This question is worth 2 points</b>.</div>";
        text += "<div class='rsq-gray'>Drag your answer choices to the spaces where they belong. To remove an answer choice, click on it.<br />To review the passage, click <b>VIEW TEXT</b>.</div>";
        this.descriptionPane.setText(text);

        this.add(this.descriptionPane);
    }

    /**************************************************
     * Actions
     **************************************************/

}
