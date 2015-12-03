package com.mocktpo.ui.tests.reading;

import com.mocktpo.model.MChoiceOption;
import com.mocktpo.model.MChoiceQuestion;
import com.mocktpo.model.MReadingParagraph;
import com.mocktpo.model.MReadingPassage;
import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.util.List;

public class ReadingPassagePanel extends BodyPanel {

    public static final int SUBJECT_PANE_HEIGHT = 60;
    public static final int OPTIONS_PANEL_HEIGHT = 300;
    public static final int OPTION_BUTTON_WIDTH = 600;
    public static final int OPTION_BUTTON_HEIGHT = 30;

    public static final int INDICATOR_PANEL_HEIGHT = 24;
    public static final int INDICATOR_LABEL_WIDTH = 100;

    private JPanel questionPanel;
    private JEditorPane subjectPane;
    private JPanel optionsPanel;

    private JPanel indicatorPanel;
    private JLabel indicatorLabel;

    private JEditorPane passagePane;

    private MReadingPassage passage;
    private int questionIndex;

    public ReadingPassagePanel(Rectangle bounds, MReadingPassage passage) {
        super(bounds);
        this.passage = passage;
        this.questionIndex = 0;
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setQuestionPanel();
        this.setIndicatorPanel();
        this.setPassagePane();
    }

    protected void setQuestionPanel() {
        this.questionPanel = new JPanel();

        int width = this.getWidth() / 2;
        int height = this.getHeight();
        this.questionPanel.setBounds(0, 0, width, height);

        this.questionPanel.setLayout(null);

        this.questionPanel.setBackground(new Color(255, 255, 255));

        MChoiceQuestion question = this.passage.getQuestions().get(questionIndex);
        String subject = question.getSubject();
        this.setSubjectPane(subject);
        List<MChoiceOption> options = question.getOptions();
        this.setOptionsPanel(options);

        this.add(this.questionPanel);
    }

    protected void setSubjectPane(String subject) {
        this.subjectPane = new JEditorPane();

        int x = LayoutConstants.MARGIN * 4;
        int y = LayoutConstants.MARGIN * 4;
        int width = this.getWidth() / 2 - LayoutConstants.MARGIN * 8;
        this.subjectPane.setBounds(x, y, width, SUBJECT_PANE_HEIGHT);

        this.subjectPane.setEditable(false);
        this.subjectPane.setOpaque(true);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".subject { color: #333333; font-family: Arial; font-size: 12px; }");
        this.subjectPane.setEditorKit(kit);
        String text = "<div class='subject'>" + subject + "</div>";
        this.subjectPane.setText(text);

        this.questionPanel.add(this.subjectPane);
    }

    protected void setOptionsPanel(List<MChoiceOption> options) {
        this.optionsPanel = new JPanel();

        this.optionsPanel.setLayout(null);

        int x = LayoutConstants.MARGIN * 4;
        int y = LayoutConstants.MARGIN * 4 + SUBJECT_PANE_HEIGHT;
        int width = this.getWidth() / 2 - LayoutConstants.MARGIN * 8;
        this.optionsPanel.setBounds(x, y, width, OPTIONS_PANEL_HEIGHT);

        this.optionsPanel.setBackground(new Color(255, 255, 255));

        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < options.size(); i++) {
            MChoiceOption option = options.get(i);

            JRadioButton radioButton = new JRadioButton(option.getText());
            radioButton.setBounds(0, OPTION_BUTTON_HEIGHT * i + LayoutConstants.MARGIN * i * 2, OPTION_BUTTON_WIDTH, OPTION_BUTTON_HEIGHT);

            radioButton.setFont(new Font("Arial", Font.PLAIN, 16));
            radioButton.setForeground(new Color(51, 51, 51));
            radioButton.setName(option.getIndex());
            ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "unchecked.png"));
            radioButton.setIcon(icon);
            ImageIcon selectedIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "checked.png"));
            radioButton.setSelectedIcon(selectedIcon);
            radioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            // radioButton.addItemListener(this);

            buttonGroup.add(radioButton);

            this.optionsPanel.add(radioButton);
        }

        this.questionPanel.add(this.optionsPanel);
    }

    protected void setIndicatorPanel() {
        this.indicatorPanel = new JPanel();

        int x = this.getWidth() / 2;
        int width = this.getWidth() / 2;
        this.indicatorPanel.setBounds(x, 0, width, INDICATOR_PANEL_HEIGHT);

        this.indicatorPanel.setLayout(null);

        this.indicatorPanel.setBackground(new Color(28, 3, 130));

        /* Set Indicator Label */

        this.indicatorLabel = new JLabel("More Available", JLabel.RIGHT);
        this.indicatorLabel.setForeground(Color.WHITE);
        this.indicatorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        int labelX = this.indicatorPanel.getWidth() - INDICATOR_LABEL_WIDTH;
        this.indicatorLabel.setBounds(labelX, 0, INDICATOR_LABEL_WIDTH, INDICATOR_PANEL_HEIGHT);

        this.indicatorPanel.add(this.indicatorLabel);

        this.add(this.indicatorPanel);
    }

    protected void setPassagePane() {
        this.passagePane = new JEditorPane();

        this.passagePane.setEditable(false);
        this.passagePane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".rp-header { color: #333333; font-family: Arial; font-size: 12px; font-weight: bold; margin-top: 20px; text-align: center; } .rp { color: #333333; font-family: Arial; font-size: 12px; margin: 20px; } .rp-gray { background-color: #cccccc; }");
        this.passagePane.setEditorKit(kit);

        String text = "<div class='rp-header'>" + passage.getTitle() + "</div>";
        text += "<div class='rp'>";
        List<MReadingParagraph> paragraphs = this.passage.getParagraphs();
        for (MReadingParagraph paragraph : paragraphs) {
            text += paragraph.getText() + "<br />";
        }
        text += "</div>";
        this.passagePane.setText(text);

        JScrollPane scrollPane = new JScrollPane();

        int x = this.getWidth() / 2;
        int y = INDICATOR_PANEL_HEIGHT;
        int width = this.getWidth() / 2;
        int height = this.getHeight() - INDICATOR_PANEL_HEIGHT;
        scrollPane.setBounds(x, y, width, height);

        scrollPane.setViewportView(this.passagePane);

        this.add(scrollPane);
    }

    /**************************************************
     * Actions
     **************************************************/

    public void nextQuestion() {
        List<MChoiceQuestion> questions = this.passage.getQuestions();
        if (questionIndex < questions.size() - 1) {
            MChoiceQuestion question = questions.get(++questionIndex);
            String subject = question.getSubject();
            this.setSubjectPane(subject);
            List<MChoiceOption> options = question.getOptions();
            this.setOptionsPanel(options);
        } else {
            // TODO
        }
    }

    public void previousQuestion() {
        List<MChoiceQuestion> questions = this.passage.getQuestions();
        if (questionIndex > 0) {
            MChoiceQuestion question = questions.get(--questionIndex);
            String subject = question.getSubject();
            this.setSubjectPane(subject);
            List<MChoiceOption> options = question.getOptions();
            this.setOptionsPanel(options);
        } else {
            // TODO
        }
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
        return true;
    }

    @Override
    public boolean nextButtonAvailable() {
        return true;
    }

    @Override
    public boolean backButtonAvailable() {
        return true;
    }

    @Override
    public boolean okButtonAvailable() {
        return false;
    }

    @Override
    public boolean helpButtonAvailable() {
        return true;
    }

    @Override
    public boolean reviewButtonAvailable() {
        return true;
    }

    @Override
    public boolean volumeButtonAvailable() {
        return false;
    }

    @Override
    public boolean continueButtonAvailable() {
        return false;
    }

    @Override
    public boolean timerLabelAvailable() {
        return true;
    }

    @Override
    public boolean hideOrShowTimerButtonAvailable() {
        return true;
    }

    /**************************************************
     * Control Buttons Status - Enabled
     **************************************************/

    @Override
    public boolean nextButtonEnabled() {
        return true;
    }

    @Override
    public boolean okButtonEnabled() {
        return true;
    }

    @Override
    public boolean helpButtonEnabled() {
        return true;
    }

    @Override
    public boolean volumeButtonEnabled() {
        return true;
    }
}
