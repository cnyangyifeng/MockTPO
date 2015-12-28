package com.mocktpo.ui.tests.reading;

import com.mocktpo.model.MReadingPassage;
import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;

public class ReadingSummaryQuestionPanel extends BodyPanel {

    public static final int DESCRIPTION_PANE_HEIGHT = 160;
    public static final int ANSWERS_PANEL_WIDTH = 500;
    public static final int ANSWERS_PANEL_HEIGHT = 340;
    public static final int SUMMARY_PANE_HEIGHT = 80;
    public static final int DOT_LABEL_WIDTH = 10;
    public static final int DOT_LABEL_HEIGHT = 10;
    public static final int ANSWER_CHOICE_HEIGHT = 80;
    public static final int SEPARATOR_LABEL_WIDTH = 200;
    public static final int SEPARATOR_LABEL_HEIGHT = 40;
    public static final int CANDIDATE_CHOICE_PANE_WIDTH = 600;
    public static final int CANDIDATE_CHOICE_PANE_HEIGHT = 60;

    protected JEditorPane descriptionPane;
    protected JPanel answersPanel;
    protected JLabel separatorLabel;

    private MReadingPassage passage;

    public ReadingSummaryQuestionPanel(Rectangle bounds, MReadingPassage passage) {
        super(bounds);
        this.passage = passage;
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setDescriptionPane();
        this.setAnswersPanel();
        this.setSeparatorLabel();
        this.setCandidateChoicePanes();
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
        style.addRule(".rsq-desc { color: #333333; font-family: Arial; font-size: 12px; margin-left: 20px; margin-top: 20px; margin-right: 20px; } .rsq-desc-gray { background-color: #cccccc; color: #333333; font-family: Arial; font-size: 12px; margin-left: 20px; margin-top: 20px; margin-right: 20px; padding: 5px; text-align: center; }");
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

        this.answersPanel.setBackground(new Color(255, 255, 255));

        this.setSummaryPane();
        this.setAnswerChoicePanes();

        this.add(this.answersPanel);
    }

    protected void setSummaryPane() {
        JEditorPane summaryPane = new JEditorPane();

        summaryPane.setBounds(0, 0, ANSWERS_PANEL_WIDTH, SUMMARY_PANE_HEIGHT);

        summaryPane.setEditable(false);
        summaryPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".rsq-answers { color: #333333; font-family: Arial; font-size: 12px; padding: 15px; }");
        summaryPane.setEditorKit(kit);
        String text = "<div class='rsq-answers'><b>Water enters, remains, and eventually leaves a lake in a variety of ways.</b></div>";
        summaryPane.setText(text);

        this.answersPanel.add(summaryPane);
    }

    protected void setAnswerChoicePanes() {
        for (int i = 0; i < 3; i++) {

            // Set dot label

            JLabel dotLabel = new JLabel();
            int dotX = LayoutConstants.MARGIN * 2;
            int dotY = SUMMARY_PANE_HEIGHT + ANSWER_CHOICE_HEIGHT * i + 5; // FIXME: 5px to make sure the dot vertical middle aligned.
            dotLabel.setBounds(dotX, dotY, DOT_LABEL_WIDTH, DOT_LABEL_HEIGHT);

            ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "dot.png"));
            dotLabel.setIcon(icon);

            this.answersPanel.add(dotLabel);

            // Set answer choice pane

            JTextPane answerChoicePane = new JTextPane();
            int x = DOT_LABEL_WIDTH + LayoutConstants.MARGIN * 4;
            int y = SUMMARY_PANE_HEIGHT + ANSWER_CHOICE_HEIGHT * i;
            int width = ANSWERS_PANEL_WIDTH - DOT_LABEL_WIDTH - LayoutConstants.MARGIN * 6;
            answerChoicePane.setBounds(x, y, width, ANSWER_CHOICE_HEIGHT);

            answerChoicePane.setDropMode(DropMode.INSERT);
            answerChoicePane.setFont(new Font("Arial", Font.PLAIN, 16));
            answerChoicePane.setText(i + " Water enters, remains, and eventually leaves.Water enters, remains, and eventually leaves.Water enters, remains, and eventually leaves.Water enters, remains, and eventually leaves.Water enters, remains, and");

            this.answersPanel.add(answerChoicePane);
        }
    }

    protected void setSeparatorLabel() {
        this.separatorLabel = new JLabel();

        int x = (this.getWidth() - SEPARATOR_LABEL_WIDTH) / 2;
        int y = this.answersPanel.getY() + ANSWERS_PANEL_HEIGHT + LayoutConstants.MARGIN * 2;
        this.separatorLabel.setBounds(x, y, SEPARATOR_LABEL_WIDTH, SEPARATOR_LABEL_HEIGHT);

        this.separatorLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.separatorLabel.setText("Answer Choices");

        this.add(this.separatorLabel);
    }

    protected void setCandidateChoicePanes() {
        for (int i = 0; i < 6; i++) {
            JTextPane choicePane = new JTextPane();

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
            choicePane.setBounds(x, y, CANDIDATE_CHOICE_PANE_WIDTH, CANDIDATE_CHOICE_PANE_HEIGHT);

            choicePane.setDragEnabled(true);
            choicePane.setFont(new Font("Arial", Font.PLAIN, 16));
            choicePane.setText(i + " Water enters, remains, and eventually leaves.Water enters, remains, and eventually leaves.Water enters, remains, and eventually leaves.Water enters, remains, and eventually leaves.Water enters, remains, and");

            this.add(choicePane);
        }
    }

    /**************************************************
     * Actions
     **************************************************/

}
