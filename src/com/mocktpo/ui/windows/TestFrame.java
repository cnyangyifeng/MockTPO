package com.mocktpo.ui.windows;

import com.mocktpo.MApplication;
import com.mocktpo.model.*;
import com.mocktpo.ui.dialogs.PauseTestDialog;
import com.mocktpo.ui.dialogs.SectionExitDialog;
import com.mocktpo.ui.tests.listening.*;
import com.mocktpo.ui.tests.misc.CopyrightPanel;
import com.mocktpo.ui.tests.misc.GeneralTestInfoPanel;
import com.mocktpo.ui.tests.reading.ReadingDirectionsPanel;
import com.mocktpo.ui.tests.reading.ReadingPassagePanel;
import com.mocktpo.ui.tests.reading.ReadingReviewPanel;
import com.mocktpo.ui.tests.reading.ReadingSummaryQuestionPanel;
import com.mocktpo.ui.widgets.*;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import com.mocktpo.util.TimeUtils;
import com.thoughtworks.xstream.XStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class TestFrame extends JFrame implements ActionListener {

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

    /* Logger */

    protected static final Logger logger = LogManager.getLogger();

    /* Frames */

    protected TestsHomeFrame testsHomeFrame;

    /* Components */

    protected HeaderPanel headerPanel;
    protected BodyPanel bodyPanel;
    protected FooterPanel footerPanel;

    protected ImageButton pauseTestButton;
    protected ImageButton sectionExitButton;
    protected StyledLabelPane questionNumberPane;
    protected ImageButton continueOvalButton;
    protected ImageButton nextButton;
    protected ImageButton backButton;
    protected ImageButton okButton;
    protected ImageButton helpButton;
    protected ImageButton reviewButton;
    protected ImageButton volumeButton;
    protected ImageButton viewTextOrQuestionButton;
    protected ImageButton continueButton;
    protected ImageButton goToQuestionButton;
    protected ImageButton returnButton;
    protected JLabel timerLabel;
    protected ImageButton hideOrShowTimeButton;

    protected CopyrightPanel copyrightPanel;
    protected GeneralTestInfoPanel generalTestInfoPanel;

    protected ReadingDirectionsPanel rdPanel;
    protected ReadingPassagePanel rpPanel;
    protected ReadingSummaryQuestionPanel rsqPanel;
    protected ReadingReviewPanel rrPanel;

    protected HeadsetPanel headsetPanel;
    protected ChangingVolumePanel cvPanel;
    protected ListeningDirectionsPanel ldPanel;
    protected ListeningPassagePanel lpPanel;
    protected ListeningHintsPanel lhPanel;
    protected ListeningQuestionPanel lqPanel;

    /* Variables */

    protected String testIndex;
    protected Rectangle bodyBounds;
    private String title;
    private Timer timer;
    private long timeElapsed;

    private MReading reading;
    private MListening listening;

    /**************************************************
     * Constructors
     **************************************************/

    public TestFrame(GraphicsConfiguration gc, TestsHomeFrame testsHomeFrame, String title) {
        super(gc);
        this.testsHomeFrame = testsHomeFrame;
        this.title = title;
        this.initComponents();
    }

    /**************************************************
     * Components Initialization
     **************************************************/

    private void initComponents() {
        /* Global settings */
        this.globalSettings();
        /* Set layout */
        this.setLayout(null);
        /* Set components */
        this.setBodyPanel();
        this.setHeaderPanel();
        this.setFooterPanel();
    }

    /**************************************************
     * Global Settings
     **************************************************/

    protected void globalSettings() {
        /* Set bounds */
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        Rectangle bounds = new Rectangle(screenSize);
        this.setBounds(bounds);
        /* Set maximized, unresizable and undecorated */
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setUndecorated(true);
        /* Set title */
        this.setTitle(this.title);
        /* Configure data */
        this.configData();
    }

    /**************************************************
     * Configure Data
     **************************************************/

    protected void configData() {
        this.configReadingData();
        this.configListeningData();
        this.timeElapsed = 3600; // 60 minutes
    }

    protected void configReadingData() {
        XStream xs = new XStream();
        xs.alias("reading", MReading.class);
        xs.alias("passage", MReadingPassage.class);
        xs.alias("paragraph", MReadingParagraph.class);
        xs.alias("question", MChoiceQuestion.class);
        xs.alias("option", MChoiceOption.class);

        this.testIndex = (String) MApplication.settings.get("testIndex");
        String val = GlobalConstants.TESTS_DIR + this.testIndex + GlobalConstants.READING_DIR + GlobalConstants.READING_CONF_FILE;
        URL xml = this.getClass().getResource(val);
        try {
            this.reading = (MReading) xs.fromXML(new File(xml.toURI()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (this.reading == null) {
            logger.error("Configuration file 'reading.xml' not found.");
            System.exit(-1);
        }
    }

    protected void configListeningData() {
        XStream xs = new XStream();
        xs.alias("listening", MListening.class);
        xs.alias("passage", MListeningPassage.class);
        xs.alias("image", MImage.class);
        xs.alias("audio", MAudio.class);
        xs.alias("question", MListeningQuestion.class);
        xs.alias("option", MChoiceOption.class);

        this.testIndex = (String) MApplication.settings.get("testIndex");
        String val = GlobalConstants.TESTS_DIR + this.testIndex + GlobalConstants.LISTENING_DIR + GlobalConstants.LISTENING_CONF_FILE;
        URL xml = this.getClass().getResource(val);
        try {
            this.listening = (MListening) xs.fromXML(new File(xml.toURI()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (this.listening == null) {
            logger.error("Configuration file 'listening.xml' not found.");
            System.exit(-1);
        }
    }

    /**************************************************
     * Body Panel Settings
     **************************************************/

    protected void setBodyPanel() {
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        this.bodyBounds = new Rectangle(0, LayoutConstants.HEADER_PANEL_HEIGHT, this.getWidth(), height);
        this.copyrightPanel = new CopyrightPanel(this.bodyBounds);
        this.bodyPanel = this.copyrightPanel;

        this.getContentPane().add(this.bodyPanel);
    }

    /**************************************************
     * Header Panel Settings
     **************************************************/

    protected void setHeaderPanel() {
        this.headerPanel = new HeaderPanel();
        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);
        this.headerPanel.setLayout(null);

        this.resetHeaderPanel();

        this.getContentPane().add(this.headerPanel);
    }

    protected void resetHeaderPanel() {
        this.headerPanel.removeAll();

        this.setLogoLabel();
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

    protected void setLogoLabel() {
        JLabel logoLabel = new JLabel();

        logoLabel.setBounds(0, LayoutConstants.MARGIN, LayoutConstants.LOGO_LABEL_WIDTH, LayoutConstants.LOGO_LABEL_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "logo.png"));
        logoLabel.setIcon(icon);

        this.headerPanel.add(logoLabel);
    }

    protected void setTitlePane() {
        /* Initialize component */
        int x = LayoutConstants.LOGO_LABEL_WIDTH + LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        String css = ".title { font-family: Impact; font-size: 11px; color: #ffffff; }";
        String html = "<div class='title'>" + this.title + "</div>";
        StyledLabelPane titlePane = new StyledLabelPane(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.headerPanel.add(titlePane);
    }

    protected void setPauseTestButton() {
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

    protected void setSectionExitButton() {
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

    protected void setQuestionNumberPane() {
        if (!this.bodyPanel.isQuestionNumberPaneAvailable()) {
            return;
        }
        /* Initialize component */
        int x = (this.headerPanel.getWidth() - QUESTION_NUMBER_PANE_WIDTH) / 2;
        int y = (this.headerPanel.getHeight() - QUESTION_NUMBER_PANE_HEIGHT) / 2;
        String css = ".question { font-family: Roboto; font-size: 11px; font-weight: bold; color: #f5f5f5; text-align: center; }";
        String html = "";
        this.questionNumberPane = new StyledLabelPane(x, y, QUESTION_NUMBER_PANE_WIDTH, QUESTION_NUMBER_PANE_HEIGHT, css, html);
        /* Reset question number */
        this.resetQuestionNumber();
        /* Add to the parent component */
        this.headerPanel.add(this.questionNumberPane);
    }

    protected void resetQuestionNumber() {
        if (this.bodyPanel instanceof ReadingPassagePanel) {
            ReadingPassagePanel rpPanel = (ReadingPassagePanel) this.bodyPanel;
            int totalQuestions = this.reading.getTotalQuestions();
            String text = "<div class='question'>Question " + rpPanel.currentQuestionIndex() + " of " + totalQuestions + "</div>";
            this.questionNumberPane.setText(text);
        }
    }

    protected void setContinueOvalButton() {
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

    protected void setNextButton() {
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

    protected void setBackButton() {
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

    protected void setOkButton() {
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

    protected void setHelpButton() {
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

    protected void setReviewButton() {
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

    protected void setVolumeButton() {
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

    protected void setViewTextOrQuestionButton() {
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

    protected void setContinueButton() {
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

    protected void setGoToQuestionButton() {
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

    protected void setReturnButton() {
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

    protected void setTimerLabel() {
        if (!this.bodyPanel.isTimerLabelAvailable()) {
            return;
        }
        this.timerLabel = new JLabel();

        int x = this.headerPanel.getWidth() - TIMER_LABEL_WIDTH - LayoutConstants.MARGIN * 2;
        int y = this.headerPanel.getHeight() - TIMER_LABEL_HEIGHT - LayoutConstants.MARGIN * 2;
        this.timerLabel.setBounds(x, y, TIMER_LABEL_WIDTH, TIMER_LABEL_HEIGHT);

        this.timerLabel.setFont(new Font("Roboto", Font.BOLD, 12));
        this.timerLabel.setForeground(new Color(245, 245, 245));
        this.timerLabel.setText(TimeUtils.displayTime(timeElapsed));
        this.stopCountdown();
        this.startCountdown();

        this.headerPanel.add(this.timerLabel);
    }

    protected void setHideOrShowTimeButton() {
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

    protected void setFooterPanel() {
        /* Initialize component */
        this.footerPanel = new FooterPanel();
        /* Set bounds */
        this.footerPanel.setBounds(0, this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);
        /* Set layout */
        this.footerPanel.setLayout(null);
        /* Set children components */
        this.setCopyrightPane();
        /* Add to the parent component */
        this.getContentPane().add(this.footerPanel);
    }

    protected void setCopyrightPane() {
        /* Initialize component */
        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        String css = ".copyright { color: #ffffff; font-family: Roboto; font-size: 8px; font-weight: bold; text-align: center; }";
        String html = "<div class='copyright'>Copyright 2006, 2010, 2011 by Educational Testing Service. All rights reserved. EDUCATIONAL TESTING SERVICE, ETS, the ETS logo, TOEFL and TOEFL iBT are registered trademarks of Educational Testing Service (ETS) in the United States and other countries.</div>";
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
                        PauseTestDialog pause = new PauseTestDialog(TestFrame.this, "", true);
                        pause.setVisible(true);
                    }
                });
                break;
            case "doSectionExit":
                logger.info("'Section Exit' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        SectionExitDialog exit = new SectionExitDialog(TestFrame.this, "", true);
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
                                    getContentPane().remove(bodyPanel);
                                    if (bodyPanel instanceof ReadingPassagePanel) {
                                        // TODO
                                        MReadingPassage passage = reading.getPassages().get(0);
                                        rsqPanel = new ReadingSummaryQuestionPanel(bodyBounds, passage);
                                        bodyPanel = rsqPanel;
                                    }
                                    getContentPane().add(bodyPanel);
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
                        getContentPane().remove(bodyPanel);
                        if (bodyPanel instanceof ReadingPassagePanel) {
                            // TODO
                            MReadingPassage passage = reading.getPassages().get(0);
                            rrPanel = new ReadingReviewPanel(bodyBounds, passage);
                            bodyPanel = rrPanel;
                        }
                        getContentPane().add(bodyPanel);
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
                        getContentPane().remove(bodyPanel);
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
                        getContentPane().add(bodyPanel);
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
                        getContentPane().remove(bodyPanel);
                        if (bodyPanel instanceof ReadingReviewPanel) {
                            // TODO
                            MReadingPassage passage = reading.getPassages().get(0);
                            rpPanel = new ReadingPassagePanel(bodyBounds, passage);
                            bodyPanel = rpPanel;
                        }
                        getContentPane().add(bodyPanel);
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
                        getContentPane().remove(bodyPanel);
                        if (bodyPanel instanceof ReadingReviewPanel) {
                            // TODO
                            MReadingPassage passage = reading.getPassages().get(0);
                            rpPanel = new ReadingPassagePanel(bodyBounds, passage);
                            bodyPanel = rpPanel;
                        }
                        getContentPane().add(bodyPanel);
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
     * Getters and setters
     **************************************************/

    public TestsHomeFrame getTestsHomeFrame() {
        return this.testsHomeFrame;
    }
}
