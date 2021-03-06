package com.mocktpo.view;

import com.mocktpo.biz.tests.listening.*;
import com.mocktpo.biz.tests.misc.CopyrightPanel;
import com.mocktpo.biz.tests.misc.GeneralTestInfoPanel;
import com.mocktpo.biz.tests.reading.ReadingDirectionsPanel;
import com.mocktpo.biz.tests.reading.ReadingPassagePanel;
import com.mocktpo.biz.tests.reading.ReadingReviewPanel;
import com.mocktpo.biz.tests.reading.ReadingSummaryQuestionPanel;
import com.mocktpo.model.*;
import com.mocktpo.util.FontsConstants;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import com.mocktpo.util.TimeUtils;
import com.mocktpo.widget.button.ImageButton;
import com.mocktpo.widget.dialog.PauseTestDialog;
import com.mocktpo.widget.dialog.SectionExitDialog;
import com.mocktpo.widget.panel.BodyPanel;
import com.mocktpo.widget.panel.FooterPanel;
import com.mocktpo.widget.panel.HeaderPanel;
import com.mocktpo.widget.panel.StyledLabelPane;
import com.mocktpo.window.AppWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestView extends MyView implements ActionListener {

    /* Constants */

    public static final int PAUSE_TEST_BUTTON_WIDTH = 84;
    public static final int PAUSE_TEST_BUTTON_HEIGHT = 34;
    public static final int SECTION_EXIT_BUTTON_WIDTH = 84;
    public static final int SECTION_EXIT_BUTTON_HEIGHT = 34;
    public static final int QUESTION_NUMBER_PANE_WIDTH = 200;
    public static final int QUESTION_NUMBER_PANE_HEIGHT = 20;
    public static final int CONTINUE_OVAL_BUTTON_WIDTH = 70;
    public static final int CONTINUE_OVAL_BUTTON_HEIGHT = 48;
    public static final int NEXT_BUTTON_WIDTH = 70;
    public static final int NEXT_BUTTON_HEIGHT = 48;
    public static final int BACK_BUTTON_WIDTH = 70;
    public static final int BACK_BUTTON_HEIGHT = 48;
    public static final int OK_BUTTON_WIDTH = 70;
    public static final int OK_BUTTON_HEIGHT = 48;
    public static final int HELP_BUTTON_WIDTH = 70;
    public static final int HELP_BUTTON_HEIGHT = 48;
    public static final int REVIEW_BUTTON_WIDTH = 70;
    public static final int REVIEW_BUTTON_HEIGHT = 48;
    public static final int VOLUME_BUTTON_WIDTH = 70;
    public static final int VOLUME_BUTTON_HEIGHT = 48;
    public static final int VIEW_TEXT_OR_QUESTION_BUTTON_WIDTH = 84;
    public static final int VIEW_TEXT_OR_QUESTION_BUTTON_HEIGHT = 34;
    public static final int CONTINUE_BUTTON_WIDTH = 74;
    public static final int CONTINUE_BUTTON_HEIGHT = 34;
    public static final int GO_TO_QUESTION_BUTTON_WIDTH = 74;
    public static final int GO_TO_QUESTION_BUTTON_HEIGHT = 34;
    public static final int RETURN_BUTTON_WIDTH = 74;
    public static final int RETURN_BUTTON_HEIGHT = 34;
    public static final int HIDE_OR_SHOW_TIME_BUTTON_WIDTH = 72;
    public static final int HIDE_OR_SHOW_TIME_BUTTON_HEIGHT = 18;
    public static final int TIMER_LABEL_WIDTH = 60;
    public static final int TIMER_LABEL_HEIGHT = 20;

    /* Components */

    private HeaderPanel headerPanel;
    private BodyPanel bodyPanel;
    private FooterPanel footerPanel;

    private ImageButton pauseTestButton;
    private ImageButton sectionExitButton;
    private StyledLabelPane questionNumberPane;
    private ImageButton continueOvalButton;
    private ImageButton nextButton;
    private ImageButton backButton;
    private ImageButton okButton;
    private ImageButton helpButton;
    private ImageButton reviewButton;
    private ImageButton volumeButton;
    private ImageButton viewTextOrQuestionButton;
    private ImageButton continueButton;
    private ImageButton goToQuestionButton;
    private ImageButton returnButton;
    private JLabel timerLabel;
    private ImageButton hideOrShowTimeButton;

    private CopyrightPanel copyrightPanel;
    private GeneralTestInfoPanel generalTestInfoPanel;

    private ReadingDirectionsPanel rdPanel;
    private ReadingPassagePanel rpPanel;
    private ReadingSummaryQuestionPanel rsqPanel;
    private ReadingReviewPanel rrPanel;

    private HeadsetPanel headsetPanel;
    private ChangingVolumePanel cvPanel;
    private ListeningDirectionsPanel ldPanel;
    private ListeningPassagePanel lpPanel;
    private ListeningHintsPanel lhPanel;
    private ListeningQuestionPanel lqPanel;

    /* Variables */

    private String testIndex;
    private Rectangle bodyBounds;
    private String title;
    private Timer timer;
    private long timeElapsed;

    private MReading reading;
    private MListening listening;

    /**************************************************
     * Constructors
     **************************************************/

    public TestView(AppWindow owner) {
        super(owner);
        this.configData();
        this.initComponents();
    }

    /**************************************************
     * Configure Data
     **************************************************/

    private void configData() {
        //this.configReadingData();
        //this.configListeningData();
        this.timeElapsed = 3600; // 60 minutes
    }

    private void configReadingData() {
//        XStream xs = new XStream();
//        xs.alias("reading", MReading.class);
//        xs.alias("passage", MReadingPassage.class);
//        xs.alias("paragraph", MReadingParagraph.class);
//        xs.alias("question", MChoiceQuestion.class);
//        xs.alias("option", MChoiceOption.class);
//
//        // this.testIndex = (String) MyApplication.settings.get("testIndex");
//        String val = GlobalConstants.TESTS_DIR + this.testIndex + GlobalConstants.READING_DIR + GlobalConstants.READING_CONF_FILE;
//        URL xml = this.getClass().getResource(val);
//        try {
//            this.reading = (MReading) xs.fromXML(new File(xml.toURI()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (this.reading == null) {
//            logger.error("Configuration file 'reading.xml' not found.");
//            System.exit(-1);
//        }
    }

    private void configListeningData() {
//        XStream xs = new XStream();
//        xs.alias("listening", MListening.class);
//        xs.alias("passage", MListeningPassage.class);
//        xs.alias("image", MImage.class);
//        xs.alias("audio", MAudio.class);
//        xs.alias("question", MListeningQuestion.class);
//        xs.alias("option", MChoiceOption.class);
//
//        // this.testIndex = (String) MyApplication.settings.get("testIndex");
//        String val = GlobalConstants.TESTS_DIR + this.testIndex + GlobalConstants.LISTENING_DIR + GlobalConstants.LISTENING_CONF_FILE;
//        URL xml = this.getClass().getResource(val);
//        try {
//            this.listening = (MListening) xs.fromXML(new File(xml.toURI()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (this.listening == null) {
//            logger.error("Configuration file 'listening.xml' not found.");
//            System.exit(-1);
//        }
    }

    /**************************************************
     * Components Initialization
     **************************************************/

    private void initComponents() {
        /* Set layout */
        this.setLayout(null);
        /* Set components */
        this.setBodyPanel();
        this.setHeaderPanel();
        this.setFooterPanel();
    }

    /**************************************************
     * Body Panel Settings
     **************************************************/

    private void setBodyPanel() {
        /* Set body bounds */
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        this.bodyBounds = new Rectangle(0, LayoutConstants.HEADER_PANEL_HEIGHT, this.getWidth(), height);
        /* Initialize body panel */
        this.bodyPanel = this.copyrightPanel = new CopyrightPanel(bodyBounds);
        /* Add body panel to the parent component */
        this.add(this.bodyPanel);
    }

    /**************************************************
     * Header Panel Settings
     **************************************************/

    private void setHeaderPanel() {
        this.headerPanel = new HeaderPanel();
        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);
        this.headerPanel.setLayout(null);

        this.resetHeaderPanel();

        this.add(this.headerPanel);
    }

    private void resetHeaderPanel() {
        this.headerPanel.removeAll();

        this.setTitlePane();
        this.setPauseTestButton();
        this.setSectionExitButton();
        this.setQuestionNumberPane();
        this.setContinueOvalButton();
        this.setNextButton();
        this.setBackButton();
        this.setOkButton();
        this.setHelpButton();
        this.setReviewButton();
        this.setVolumeButton();
        this.setViewTextOrQuestionButton();
        this.setContinueButton();
        this.setGoToQuestionButton();
        this.setReturnButton();
        this.setTimerLabel();
        this.setHideOrShowTimeButton();
    }

    private void setTitlePane() {
        /* Initialize component */
        int x = LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        String css = ".title { font-family: " + FontsConstants.LOGO_FONT + "; font-size: 12px; color: #ffffff; }";
        String html = "<div class='title'>" + this.title + "</div>";
        StyledLabelPane titlePane = new StyledLabelPane(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.headerPanel.add(titlePane);
    }

    private void setPauseTestButton() {
        /* Initialize component */
        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - PAUSE_TEST_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "pause_test.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "pause_test_hi.png"));
        this.pauseTestButton = new ImageButton(x, y, PAUSE_TEST_BUTTON_WIDTH, PAUSE_TEST_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.pauseTestButton.setActionCommand("doPauseTest");
        this.pauseTestButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.pauseTestButton);
    }

    private void setSectionExitButton() {
        if (!this.bodyPanel.isSectionExitButtonAvailable()) {
            return;
        }
        /* Initialize component */
        int x = this.pauseTestButton.getX() + PAUSE_TEST_BUTTON_WIDTH + LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - SECTION_EXIT_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "section_exit.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "section_exit_hi.png"));
        this.sectionExitButton = new ImageButton(x, y, SECTION_EXIT_BUTTON_WIDTH, SECTION_EXIT_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.sectionExitButton.setActionCommand("doSectionExit");
        this.sectionExitButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.sectionExitButton);
    }

    private void setQuestionNumberPane() {
        if (!this.bodyPanel.isQuestionNumberPaneAvailable()) {
            return;
        }
        /* Initialize component */
        int x = (this.headerPanel.getWidth() - QUESTION_NUMBER_PANE_WIDTH) / 2;
        int y = (this.headerPanel.getHeight() - QUESTION_NUMBER_PANE_HEIGHT) / 2;
        String css = ".question { font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 11px; font-weight: bold; color: #f5f5f5; text-align: center; }";
        String html = "";
        this.questionNumberPane = new StyledLabelPane(x, y, QUESTION_NUMBER_PANE_WIDTH, QUESTION_NUMBER_PANE_HEIGHT, css, html);
        /* Reset question number */
        this.resetQuestionNumber();
        /* Add to the parent component */
        this.headerPanel.add(this.questionNumberPane);
    }

    private void resetQuestionNumber() {
        if (this.bodyPanel instanceof ReadingPassagePanel) {
            ReadingPassagePanel rpPanel = (ReadingPassagePanel) this.bodyPanel;
            int totalQuestions = this.reading.getTotalQuestions();
            String text = "<div class='question'>Question " + rpPanel.currentQuestionIndex() + " of " + totalQuestions + "</div>";
            this.questionNumberPane.setText(text);
        }
    }

    private void setContinueOvalButton() {
        if (!this.bodyPanel.isContinueOvalButtonAvailable()) {
            return;
        }
        /* Initialize component */
        int x = this.headerPanel.getWidth() - CONTINUE_OVAL_BUTTON_WIDTH - LayoutConstants.MARGIN;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "continue_oval.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "continue_oval_hi.png"));
        this.continueOvalButton = new ImageButton(x, 0, CONTINUE_OVAL_BUTTON_WIDTH, CONTINUE_OVAL_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.continueOvalButton.setActionCommand("doContinueOval");
        this.continueOvalButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.continueOvalButton);
    }

    private void setNextButton() {
        if (!this.bodyPanel.isNextButtonAvailable()) {
            return;
        }
        /* Initialize component */
        int x = this.headerPanel.getWidth() - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "next.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "next_hi.png"));
        this.nextButton = new ImageButton(x, 0, NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set enabled */
        this.nextButton.setEnabled(this.bodyPanel.isNextButtonEnabled());
        /* Set actions */
        this.nextButton.setActionCommand("doNext");
        this.nextButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.nextButton);
    }

    private void setBackButton() {
        if (!this.bodyPanel.isBackButtonAvailable()) {
            return;
        }
        /* Initialize component */
        int x = this.headerPanel.getWidth() - BACK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 2;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "back.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "back_hi.png"));
        this.backButton = new ImageButton(x, 0, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set enabled */
        this.backButton.setEnabled(this.bodyPanel.isBackButtonEnabled());
        /* Set actions */
        this.backButton.setActionCommand("doBack");
        this.backButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.backButton);
    }

    private void setOkButton() {
        if (!this.bodyPanel.isOkButtonAvailable()) {
            return;
        }
        /* Initialize component */
        int x = this.headerPanel.getWidth() - OK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 2;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "ok.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "ok_hi.png"));
        this.okButton = new ImageButton(x, 0, OK_BUTTON_WIDTH, OK_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set enabled */
        this.okButton.setEnabled(this.bodyPanel.isOkButtonEnabled());
        /* Set actions */
        this.okButton.setActionCommand("doOk");
        this.okButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.okButton);
    }

    private void setHelpButton() {
        if (!this.bodyPanel.isHelpButtonAvailable()) {
            return;
        }
        /* Initialize component */
        int x = this.headerPanel.getWidth() - HELP_BUTTON_WIDTH - OK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 3;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "help.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "help_hi.png"));
        this.helpButton = new ImageButton(x, 0, HELP_BUTTON_WIDTH, HELP_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set enabled */
        this.helpButton.setEnabled(this.bodyPanel.isHelpButtonEnabled());
        /* Set actions */
        this.helpButton.setActionCommand("doHelp");
        this.helpButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.helpButton);
    }

    private void setReviewButton() {
        if (!this.bodyPanel.isReviewButtonAvailable()) {
            return;
        }
        /* Initialize component */
        int x = this.headerPanel.getWidth() - REVIEW_BUTTON_WIDTH - LayoutConstants.MARGIN;
        if (this.bodyPanel.isNextButtonAvailable() && this.bodyPanel.isBackButtonAvailable() && this.bodyPanel.isHelpButtonAvailable()) {
            x = this.headerPanel.getWidth() - REVIEW_BUTTON_WIDTH - HELP_BUTTON_WIDTH - BACK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 4;
        }
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "review.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "review_hi.png"));
        this.reviewButton = new ImageButton(x, 0, REVIEW_BUTTON_WIDTH, REVIEW_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set enabled */
        this.reviewButton.setEnabled(this.bodyPanel.isReviewButtonEnabled());
        /* Set actions */
        this.reviewButton.setActionCommand("doReview");
        this.reviewButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.reviewButton);
    }

    private void setVolumeButton() {
        if (!this.bodyPanel.isVolumeButtonAvailable()) {
            return;
        }
        /* Initialize component */
        int x = this.headerPanel.getWidth() - VOLUME_BUTTON_WIDTH - LayoutConstants.MARGIN;
        if (this.bodyPanel.isNextButtonAvailable() && this.bodyPanel.isOkButtonAvailable() && this.bodyPanel.isHelpButtonAvailable()) {
            x = this.headerPanel.getWidth() - VOLUME_BUTTON_WIDTH - HELP_BUTTON_WIDTH - OK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 4;
        }
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "volume.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "volume_hi.png"));
        this.volumeButton = new ImageButton(x, 0, VOLUME_BUTTON_WIDTH, VOLUME_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set enabled */
        this.volumeButton.setEnabled(this.bodyPanel.isVolumeButtonEnabled());
        /* Set actions */
        this.volumeButton.setActionCommand("doVolume");
        this.volumeButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.volumeButton);
    }

    private void setViewTextOrQuestionButton() {
        if (!this.bodyPanel.isViewTextOrQuestionButtonAvailable()) {
            return;
        }
        /* Initialize component */
        int x = this.headerPanel.getWidth() - VIEW_TEXT_OR_QUESTION_BUTTON_WIDTH - LayoutConstants.MARGIN;
        if (this.bodyPanel.isNextButtonAvailable() && this.bodyPanel.isBackButtonAvailable() && this.bodyPanel.isHelpButtonAvailable() && this.bodyPanel.isReviewButtonAvailable()) {
            x = this.headerPanel.getWidth() - VIEW_TEXT_OR_QUESTION_BUTTON_WIDTH - REVIEW_BUTTON_WIDTH - HELP_BUTTON_WIDTH - BACK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 6;
        }
        int y = LayoutConstants.MARGIN * 3;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "view_text.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "view_text_hi.png"));
        this.viewTextOrQuestionButton = new ImageButton(x, y, VIEW_TEXT_OR_QUESTION_BUTTON_WIDTH, VIEW_TEXT_OR_QUESTION_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.viewTextOrQuestionButton.setActionCommand("doViewText");
        this.viewTextOrQuestionButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.viewTextOrQuestionButton);
    }

    private void setContinueButton() {
        if (!this.bodyPanel.isContinueButtonAvailable()) {
            return;
        }
        /* Initialize component */
        int x = this.headerPanel.getWidth() - CONTINUE_BUTTON_WIDTH - LayoutConstants.MARGIN;
        if (this.bodyPanel.isNextButtonAvailable() && this.bodyPanel.isBackButtonAvailable() && this.bodyPanel.isHelpButtonAvailable() && this.bodyPanel.isReviewButtonAvailable()) {
            x = this.headerPanel.getWidth() - CONTINUE_BUTTON_WIDTH - REVIEW_BUTTON_WIDTH - HELP_BUTTON_WIDTH - BACK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 6;
        } else if (this.bodyPanel.isNextButtonAvailable() && this.bodyPanel.isOkButtonAvailable() && this.bodyPanel.isHelpButtonAvailable() && this.bodyPanel.isVolumeButtonAvailable()) {
            x = this.headerPanel.getWidth() - CONTINUE_BUTTON_WIDTH - VOLUME_BUTTON_WIDTH - HELP_BUTTON_WIDTH - OK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 6;
        } else if (!this.bodyPanel.isNextButtonAvailable() && !this.bodyPanel.isOkButtonAvailable() && !this.bodyPanel.isHelpButtonAvailable() && this.bodyPanel.isVolumeButtonAvailable()) {
            x = this.headerPanel.getWidth() - CONTINUE_BUTTON_WIDTH - VOLUME_BUTTON_WIDTH - LayoutConstants.MARGIN * 2;
        }
        int y = LayoutConstants.MARGIN * 3;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "continue.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "continue_hi.png"));
        this.continueButton = new ImageButton(x, y, CONTINUE_BUTTON_WIDTH, CONTINUE_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.continueButton.setActionCommand("doContinue");
        this.continueButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.continueButton);
    }

    private void setGoToQuestionButton() {
        if (!this.bodyPanel.isGoToQuestionButtonAvailable()) {
            return;
        }
        /* Initialize component */
        int x = this.headerPanel.getWidth() - GO_TO_QUESTION_BUTTON_WIDTH - LayoutConstants.MARGIN;
        int y = LayoutConstants.MARGIN * 3;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "go_to_question.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "go_to_question_hi.png"));
        this.goToQuestionButton = new ImageButton(x, y, GO_TO_QUESTION_BUTTON_WIDTH, GO_TO_QUESTION_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.goToQuestionButton.setActionCommand("doGoToQuestion");
        this.goToQuestionButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.goToQuestionButton);
    }

    private void setReturnButton() {
        if (!this.bodyPanel.isReturnButtonAvailable()) {
            return;
        }
        if (this.bodyPanel.isGoToQuestionButtonAvailable() && this.bodyPanel.isReturnButtonAvailable()) {
            /* Initialize component */
            int x = this.headerPanel.getWidth() - GO_TO_QUESTION_BUTTON_WIDTH - RETURN_BUTTON_WIDTH - LayoutConstants.MARGIN * 2;
            int y = LayoutConstants.MARGIN * 3;
            ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "return.png"));
            ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "return_hi.png"));
            this.returnButton = new ImageButton(x, y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT, icon, rolloverIcon);
        } else {
            return;
        }
        /* Set actions */
        this.returnButton.setActionCommand("doReturn");
        this.returnButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.returnButton);
    }

    private void setTimerLabel() {
        if (!this.bodyPanel.isTimerLabelAvailable()) {
            return;
        }
        this.timerLabel = new JLabel();

        int x = this.headerPanel.getWidth() - TIMER_LABEL_WIDTH - LayoutConstants.MARGIN * 2;
        int y = this.headerPanel.getHeight() - TIMER_LABEL_HEIGHT - LayoutConstants.MARGIN * 2;
        this.timerLabel.setBounds(x, y, TIMER_LABEL_WIDTH, TIMER_LABEL_HEIGHT);

        this.timerLabel.setFont(new Font(FontsConstants.SYSTEM_FONT, Font.BOLD, 12));
        this.timerLabel.setForeground(new Color(245, 245, 245));
        this.timerLabel.setText(TimeUtils.displayTime(timeElapsed));
        this.stopCountdown();
        this.startCountdown();

        this.headerPanel.add(this.timerLabel);
    }

    private void setHideOrShowTimeButton() {
        if (!this.bodyPanel.isHideOrShowTimerButtonAvailable()) {
            return;
        }
        /* Initialize component */
        int x = this.headerPanel.getWidth() - HIDE_OR_SHOW_TIME_BUTTON_WIDTH - TIMER_LABEL_WIDTH - LayoutConstants.MARGIN * 4;
        int y = this.headerPanel.getHeight() - HIDE_OR_SHOW_TIME_BUTTON_HEIGHT - LayoutConstants.MARGIN * 2;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "hide_time.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "hide_time_hi.png"));
        this.hideOrShowTimeButton = new ImageButton(x, y, HIDE_OR_SHOW_TIME_BUTTON_WIDTH, HIDE_OR_SHOW_TIME_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.hideOrShowTimeButton.setActionCommand("doHideOrShowTimer");
        this.hideOrShowTimeButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(this.hideOrShowTimeButton);
    }

    /**************************************************
     * Set Footer Panel
     **************************************************/

    private void setFooterPanel() {
        /* Initialize component */
        this.footerPanel = new FooterPanel();
        /* Set bounds */
        this.footerPanel.setBounds(0, this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);
        /* Set layout */
        this.footerPanel.setLayout(null);
        /* Set children components */
        this.setCopyrightPane();
        /* Add to the parent component */
        this.add(this.footerPanel);
    }

    private void setCopyrightPane() {
        /* Initialize component */
        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        String css = ".copyright { color: #ffffff; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 8px; font-weight: bold; text-align: center; }";
        String html = "<div class='copyright'>" + GlobalConstants.COPYRIGHT_INFO + "</div>";
        StyledLabelPane copyrightPane = new StyledLabelPane(x, y, LayoutConstants.COPYRIGHT_PANE_WIDTH, LayoutConstants.COPYRIGHT_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.footerPanel.add(copyrightPane);
    }

    /**************************************************
     * Countdown
     **************************************************/

    public void startCountdown() {
        this.timer = new Timer(1000, this);
        this.timer.setActionCommand("doCountdown");
        this.timer.start();
    }

    public void stopCountdown() {
        if (this.timer != null) {
            this.timer.stop();
        }
    }

    /**************************************************
     * Listener Implementations
     **************************************************/

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        switch (ac) {
            case "doCountdown":
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        timeElapsed--;
                        timerLabel.setText(TimeUtils.displayTime(timeElapsed));
                        if (timeElapsed <= 0) {
                            timer.stop();
                            // TODO
                        }
                    }
                });
                break;
            case "doPauseTest":
                logger.info("'Pause Test' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        PauseTestDialog pause = new PauseTestDialog(TestView.this.getAppWindow(), "", true);
                        pause.setVisible(true);
                    }
                });
                break;
            case "doSectionExit":
                logger.info("'Section Exit' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        SectionExitDialog exit = new SectionExitDialog(TestView.this.getAppWindow(), "", true);
                        exit.setVisible(true);
                    }
                });
                break;
            case "doContinueOval":
                logger.info("'Continue' oval button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        bodyPanel.setContinueOvalButtonAvailable(false);
                        bodyPanel.setQuestionNumberPaneAvailable(true);
                        bodyPanel.setNextButtonAvailable(true);
                        bodyPanel.setBackButtonAvailable(true);
                        bodyPanel.setHelpButtonAvailable(true);
                        bodyPanel.setReviewButtonAvailable(true);
                        bodyPanel.setNextButtonEnabled(true);
                        bodyPanel.setBackButtonEnabled(true);
                        bodyPanel.setHelpButtonEnabled(true);
                        bodyPanel.setReviewButtonEnabled(true);
                        resetHeaderPanel();
                        repaint();
                    }
                });
                break;
            case "doNext":
                logger.info("'Next' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (bodyPanel instanceof ReadingPassagePanel) {
//                            ((ReadingPassagePanel) bodyPanel).nextQuestion();
//                            resetQuestionNumber();

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    TestView.this.remove(bodyPanel);
                                    if (bodyPanel instanceof ReadingPassagePanel) {
                                        // TODO
                                        MReadingPassage passage = reading.getPassages().get(0);
                                        rsqPanel = new ReadingSummaryQuestionPanel(bodyBounds, passage);
                                        bodyPanel = rsqPanel;
                                    }
                                    TestView.this.add(bodyPanel);
                                    resetHeaderPanel();
                                    repaint();
                                }
                            });
// TODO END
                        }
                    }
                });
                break;
            case "doBack":
                logger.info("'Back' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (bodyPanel instanceof ReadingPassagePanel) {
                            ((ReadingPassagePanel) bodyPanel).previousQuestion();
                            resetQuestionNumber();
                        }
                    }
                });
                break;
            case "doOk":
                logger.info("'Ok' button pressed.");
                break;
            case "doHelp":
                logger.info("'Help' button pressed.");
                break;
            case "doReview":
                logger.info("'Review' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        TestView.this.remove(bodyPanel);
                        if (bodyPanel instanceof ReadingPassagePanel) {
                            // TODO
                            MReadingPassage passage = reading.getPassages().get(0);
                            rrPanel = new ReadingReviewPanel(bodyBounds, passage);
                            bodyPanel = rrPanel;
                        }
                        TestView.this.add(bodyPanel);
                        resetHeaderPanel();
                        repaint();
                    }
                });
                break;
            case "doVolume":
                logger.info("'Volume' button pressed.");
                break;
            case "doContinue":
                logger.info("'Continue' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        TestView.this.remove(bodyPanel);
                        if (bodyPanel instanceof CopyrightPanel) {
                            generalTestInfoPanel = new GeneralTestInfoPanel(bodyBounds);
                            bodyPanel = generalTestInfoPanel;
                        } else if (bodyPanel instanceof GeneralTestInfoPanel) {
                            rdPanel = new ReadingDirectionsPanel(bodyBounds);
                            bodyPanel = rdPanel;
                        } else if (bodyPanel instanceof ReadingDirectionsPanel) {
                            // TODO
                            MReadingPassage passage = reading.getPassages().get(0);
                            rpPanel = new ReadingPassagePanel(bodyBounds, passage);
                            bodyPanel = rpPanel;
                        } else if (bodyPanel instanceof ReadingPassagePanel) {
                            headsetPanel = new HeadsetPanel(bodyBounds);
                            bodyPanel = headsetPanel;
                        } else if (bodyPanel instanceof HeadsetPanel) {
                            cvPanel = new ChangingVolumePanel(bodyBounds);
                            bodyPanel = cvPanel;
                        } else if (bodyPanel instanceof ChangingVolumePanel) {
                            ldPanel = new ListeningDirectionsPanel(bodyBounds);
                            bodyPanel = ldPanel;
                        } else if (bodyPanel instanceof ListeningDirectionsPanel) {
                            MListeningPassage passage = listening.getPassages().get(0);
                            System.out.println("passage: " + passage.getImages());
                            lpPanel = new ListeningPassagePanel(bodyBounds, passage);
                            bodyPanel = lpPanel;
                        } else if (bodyPanel instanceof ListeningPassagePanel) {
                            String hints = "Now get ready to answer the questions.<br /><br />You may use your notes<br /> to help you answer.";
                            lhPanel = new ListeningHintsPanel(bodyBounds, hints);
                            bodyPanel = lhPanel;
                        } else if (bodyPanel instanceof ListeningHintsPanel) {
                            MListeningQuestion question = listening.getPassages().get(0).getQuestions().get(0);
                            lqPanel = new ListeningQuestionPanel(bodyBounds, question);
                            bodyPanel = lqPanel;
                        }
                        TestView.this.add(bodyPanel);
                        resetHeaderPanel();
                        repaint();
                    }
                });
                break;
            case "doGoToQuestion":
                logger.info("'Go To Question' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        TestView.this.remove(bodyPanel);
                        if (bodyPanel instanceof ReadingReviewPanel) {
                            // TODO
                            MReadingPassage passage = reading.getPassages().get(0);
                            rpPanel = new ReadingPassagePanel(bodyBounds, passage);
                            bodyPanel = rpPanel;
                        }
                        TestView.this.add(bodyPanel);
                        resetHeaderPanel();
                        repaint();
                    }
                });
                break;
            case "doReturn":
                logger.info("'Return' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        TestView.this.remove(bodyPanel);
                        if (bodyPanel instanceof ReadingReviewPanel) {
                            // TODO
                            MReadingPassage passage = reading.getPassages().get(0);
                            rpPanel = new ReadingPassagePanel(bodyBounds, passage);
                            bodyPanel = rpPanel;
                        }
                        TestView.this.add(bodyPanel);
                        resetHeaderPanel();
                        repaint();
                    }
                });
                break;
            case "doHideOrShowTimer":
                logger.info("'Hide Or Show Timer' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (timerLabel.isVisible()) {
                            // Reset timerLabel
                            timerLabel.setVisible(false);
                            // Reset hideOrShowTimerButton
                            ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "show_time.png"));
                            hideOrShowTimeButton.setIcon(icon);
                            ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "show_time_hi.png"));
                            hideOrShowTimeButton.setRolloverIcon(rolloverIcon);
                        } else {
                            // Reset timerLabel
                            timerLabel.setVisible(true);
                            // Reset hideOrShowTimerButton
                            ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "hide_time.png"));
                            hideOrShowTimeButton.setIcon(icon);
                            ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "hide_time_hi.png"));
                            hideOrShowTimeButton.setRolloverIcon(rolloverIcon);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    /**************************************************
     * Getters and Setters
     **************************************************/

    // TODO Getters and setters
}
