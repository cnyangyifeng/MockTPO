package com.mocktpo.view.tests.reading;

import com.mocktpo.model.MReadingPassage;
import com.mocktpo.view.widgets.BodyPanel;
import com.mocktpo.util.FontsConstants;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;

public class ReadingSummaryQuestionPanel extends BodyPanel {

    /* Constants */

    public static final int DESCRIPTION_PANE_HEIGHT = 160;
    public static final int ANSWERS_PANEL_WIDTH = 500;
    public static final int ANSWERS_PANEL_HEIGHT = 340;
    public static final int SUMMARY_PANE_WIDTH = 500;
    public static final int SUMMARY_PANE_HEIGHT = 80;
    public static final int DOT_LABEL_WIDTH = 10;
    public static final int DOT_LABEL_HEIGHT = 10;
    public static final int ANSWER_CHOICE_HEIGHT = 80;
    public static final int SEPARATOR_LABEL_WIDTH = 140;
    public static final int SEPARATOR_LABEL_HEIGHT = 40;
    public static final int CANDIDATE_CHOICE_PANE_WIDTH = 600;
    public static final int CANDIDATE_CHOICE_PANE_HEIGHT = 60;

    /* Components */

    protected JEditorPane descriptionPane;
    protected JPanel answersPanel;
    protected JEditorPane summaryPane;

    protected JTextPane[] answerChoicePanes;
    protected JLabel separatorLabel;
    protected JTextPane[] candidateChoicePanes;

    /* Variables */

    private MReadingPassage passage;

    /**************************************************
     * Constructors
     **************************************************/

    public ReadingSummaryQuestionPanel(Rectangle bounds, MReadingPassage passage) {
        super(bounds);
        this.passage = passage;
        this.initComponents();
    }

    /**************************************************
     * Components Initialization
     **************************************************/

    private void initComponents() {
        /* Set layout */
        this.setLayout(null);
        /* Set components */
        this.setDescriptionPane();
        this.setAnswersPanel();
        this.setSummaryPane();
        this.setAnswerChoicePanes();
        this.setSeparatorLabel();
        this.setCandidateChoicePanes();
        /* Set drag and drop features */
        this.setDragAndDrop();
    }

    @Override
    protected void initButtonStatus() {
        this.setQuestionNumberPaneAvailable(true);
        this.setNextButtonAvailable(true);
        this.setBackButtonAvailable(true);
        this.setHelpButtonAvailable(true);
        this.setReviewButtonAvailable(true);
        this.setViewTextOrQuestionButtonAvailable(true);
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
        style.addRule(".rsq-desc { color: #333333; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 12px; margin-left: 20px; margin-top: 20px; margin-right: 20px; } .rsq-desc-gray { background-color: #cccccc; color: #333333; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 12px; margin-left: 20px; margin-top: 20px; margin-right: 20px; padding: 5px; text-align: center; }");
        this.descriptionPane.setEditorKit(kit);
        String text = "<div class='rsq-desc'><b>Directions</b>: An introductory sentence for a brief summary of the passage is provided below. Complete the summary by selecting the THREE answer choices that express the most important ideas in the passage. Some sentences do not belong in the summary because they express ideas that are not presented in the passage or are minor ideas in the passage. <b>This question is worth 2 points</b>.</div>";
        text += "<div class='rsq-desc-gray'>Drag your answer choices to the spaces where they belong. To remove an answer choice, click on it.<br />To review the passage, click <b>VIEW TEXT</b>.</div>";
        this.descriptionPane.setText(text);

        this.add(this.descriptionPane);
    }

    protected void setAnswersPanel() {
        this.answersPanel = new JPanel();

        int x = (this.getWidth() - ANSWERS_PANEL_WIDTH) / 2;
        int y = this.descriptionPane.getY() + DESCRIPTION_PANE_HEIGHT;
        this.answersPanel.setBounds(x, y, ANSWERS_PANEL_WIDTH, ANSWERS_PANEL_HEIGHT);
        this.answersPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        this.answersPanel.setLayout(null);
        this.answersPanel.setOpaque(false);

        this.add(this.answersPanel);
    }

    protected void setSummaryPane() {
        this.summaryPane = new JEditorPane();

        int x = this.answersPanel.getX();
        int y = this.answersPanel.getY();
        this.summaryPane.setBounds(x, y, SUMMARY_PANE_WIDTH, SUMMARY_PANE_HEIGHT);

        this.summaryPane.setEditable(false);
        this.summaryPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".rsq-answers { color: #333333; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 12px; padding: 15px; }");
        this.summaryPane.setEditorKit(kit);
        String text = "<div class='rsq-answers'><b>Water enters, remains, and eventually leaves a lake in a variety of ways.</b></div>";
        this.summaryPane.setText(text);

        this.add(this.summaryPane);
    }

    protected void setAnswerChoicePanes() {
        answerChoicePanes = new JTextPane[3];

        for (int i = 0; i < answerChoicePanes.length; i++) {

            // Set dot label

            JLabel dotLabel = new JLabel();
            int dotX = this.answersPanel.getX() + LayoutConstants.MARGIN * 2;
            int dotY = this.answersPanel.getY() + SUMMARY_PANE_HEIGHT + ANSWER_CHOICE_HEIGHT * i + 5; // FIXME: 5px to make sure the dot vertical middle aligned.
            dotLabel.setBounds(dotX, dotY, DOT_LABEL_WIDTH, DOT_LABEL_HEIGHT);

            ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "dot.png"));
            dotLabel.setIcon(icon);

            this.add(dotLabel);

            // Set answer choice pane

            answerChoicePanes[i] = new JTextPane();
            int x = this.answersPanel.getX() + DOT_LABEL_WIDTH + LayoutConstants.MARGIN * 4;
            int y = this.answersPanel.getY() + SUMMARY_PANE_HEIGHT + ANSWER_CHOICE_HEIGHT * i;
            int width = ANSWERS_PANEL_WIDTH - DOT_LABEL_WIDTH - LayoutConstants.MARGIN * 6;
            answerChoicePanes[i].setBounds(x, y, width, ANSWER_CHOICE_HEIGHT);

            answerChoicePanes[i].setFont(new Font(FontsConstants.SYSTEM_FONT, Font.PLAIN, 16));
            answerChoicePanes[i].setText(i + " Water enters");

            this.add(answerChoicePanes[i]);
        }
    }

    protected void setSeparatorLabel() {
        this.separatorLabel = new JLabel();

        int x = (this.getWidth() - SEPARATOR_LABEL_WIDTH) / 2;
        int y = this.answersPanel.getY() + ANSWERS_PANEL_HEIGHT + LayoutConstants.MARGIN * 2;
        this.separatorLabel.setBounds(x, y, SEPARATOR_LABEL_WIDTH, SEPARATOR_LABEL_HEIGHT);

        this.separatorLabel.setFont(new Font(FontsConstants.SYSTEM_FONT, Font.BOLD, 16));
        this.separatorLabel.setText("Answer Choices");

        this.add(this.separatorLabel);
    }

    protected void setCandidateChoicePanes() {
        candidateChoicePanes = new JTextPane[6];

        for (int i = 0; i < candidateChoicePanes.length; i++) {

            // Set candidate choice pane

            candidateChoicePanes[i] = new JTextPane();

            int x = (this.getWidth() - CANDIDATE_CHOICE_PANE_WIDTH * 2 - LayoutConstants.MARGIN * 10) / 2;
            if (i % 2 == 1) {
                x += CANDIDATE_CHOICE_PANE_WIDTH + LayoutConstants.MARGIN * 10;
            }
            int y = this.separatorLabel.getY() + SEPARATOR_LABEL_HEIGHT + LayoutConstants.MARGIN * 2;
            if (i / 2 == 1) {
                y += CANDIDATE_CHOICE_PANE_HEIGHT + LayoutConstants.MARGIN;
            } else if (i / 2 == 2) {
                y += (CANDIDATE_CHOICE_PANE_HEIGHT + LayoutConstants.MARGIN) * 2;
            }
            candidateChoicePanes[i].setBounds(x, y, CANDIDATE_CHOICE_PANE_WIDTH, CANDIDATE_CHOICE_PANE_HEIGHT);

            candidateChoicePanes[i].setFont(new Font(FontsConstants.SYSTEM_FONT, Font.PLAIN, 16));
            candidateChoicePanes[i].setText(i + " Water enters, remains, and eventually leaves.Water enters, remains, and eventually leaves.Water enters, remains, and eventually leaves.Water enters, remains, and eventually leaves.Water enters, remains, and");

            this.add(candidateChoicePanes[i]);
        }
    }

    protected void setDragAndDrop() {
        // TODO dnd
    }

    /**************************************************
     * Actions
     **************************************************/

}
