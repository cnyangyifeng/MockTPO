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
import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.ui.widgets.FooterPanel;
import com.mocktpo.ui.widgets.HeaderPanel;
import com.mocktpo.ui.widgets.MButton;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import com.thoughtworks.xstream.XStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class TestFrame extends JFrame implements ActionListener {

    // Constants

    public static final int PAUSE_TEST_BUTTON_WIDTH = 84;
    public static final int PAUSE_TEST_BUTTON_HEIGHT = 34;
    public static final int SECTION_EXIT_BUTTON_WIDTH = 84;
    public static final int SECTION_EXIT_BUTTON_HEIGHT = 34;

    public static final int QUESTION_NUMBER_PANE_WIDTH = 200;
    public static final int QUESTION_NUMBER_PANE_HEIGHT = 20;
    public static final int NEXT_BUTTON_WIDTH = 70;
    public static final int NEXT_BUTTON_HEIGHT = 48;
    public static final int OK_BUTTON_WIDTH = 70;
    public static final int OK_BUTTON_HEIGHT = 48;
    public static final int HELP_BUTTON_WIDTH = 70;
    public static final int HELP_BUTTON_HEIGHT = 48;
    public static final int VOLUME_BUTTON_WIDTH = 70;
    public static final int VOLUME_BUTTON_HEIGHT = 48;
    public static final int CONTINUE_BUTTON_WIDTH = 74;
    public static final int CONTINUE_BUTTON_HEIGHT = 34;

    public static final int HIDE_OR_SHOW_TIMER_BUTTON_WIDTH = 72;
    public static final int HIDE_OR_SHOW_TIMER_BUTTON_HEIGHT = 18;
    public static final int TIMER_LABEL_WIDTH = 60;
    public static final int TIMER_LABEL_HEIGHT = 20;

    // Logger

    protected static final Logger logger = LogManager.getLogger();

    protected MainFrame mainFrame;

    // Widgets

    protected HeaderPanel headerPanel;
    protected BodyPanel bodyPanel;
    protected FooterPanel footerPanel;

    protected MButton pauseTestButton;
    private MButton sectionExitButton;

    private JEditorPane questionNumberPane;
    private MButton nextButton;
    private MButton okButton;
    private MButton helpButton;
    private MButton volumeButton;
    private MButton continueButton;

    private JLabel timerLabel;
    private MButton hideOrShowTimerButton;

    // Panels

    private CopyrightPanel copyrightPanel;
    private GeneralTestInfoPanel generalTestInfoPanel;

    private ReadingDirectionsPanel rdPanel;
    private ReadingPassagePanel rpPanel;

    private HeadsetPanel headsetPanel;
    private ChangingVolumePanel cvPanel;
    private ListeningDirectionsPanel ldPanel;
    private ListeningPassagePanel lpPanel;
    private ListeningHintsPanel lhPanel;
    private ListeningQuestionPanel lqPanel;

    // Variables

    protected Rectangle bodyBounds;
    protected String testIndex;

    private MReading reading;
    private MListening listening;
    private int nextQuestion = 0;

    public TestFrame(GraphicsConfiguration gc, MainFrame mainFrame) {
        super(gc);
        this.mainFrame = mainFrame;
        this.initComponents();
    }

    protected void initComponents() {
        this.globalSettings();
        this.setLayout(null);

        this.setHeaderPanel();
        this.setBodyPanel();
        this.setFooterPanel();
    }

    protected void globalSettings() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        Rectangle bounds = new Rectangle(screenSize);
        this.setBounds(bounds);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setUndecorated(true);

        this.setTitle("TOEFL iBT Complete Practice Test");
    }

    /**************************************************
     * Configure Data
     **************************************************/

    protected void configData() {
        this.configReadingData();
        this.configListeningData();
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
    }

    /**************************************************
     * Set Header Panel
     **************************************************/

    protected void setHeaderPanel() {
        this.headerPanel = new HeaderPanel();
        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);
        this.headerPanel.setLayout(null);

        this.setLogoLabel();
        this.setTitlePane();
        this.setPauseTestButton();

        this.setSectionExitButton();
        this.setQuestionNumberPane();

        this.setNextButton();
        this.setOkButton();
        this.setHelpButton();
        this.setVolumeButton();
        this.setContinueButton();

        this.setTimerLabel();
        this.setHideOrShowTimerButton();

        this.getContentPane().add(headerPanel);
    }

    protected void setLogoLabel() {
        JLabel logoLabel = new JLabel();

        logoLabel.setBounds(0, LayoutConstants.MARGIN, LayoutConstants.LOGO_LABEL_WIDTH, LayoutConstants.LOGO_LABEL_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "logo.png"));
        logoLabel.setIcon(icon);

        this.headerPanel.add(logoLabel);
    }

    protected void setTitlePane() {
        JEditorPane titlePane = new JEditorPane();

        int x = LayoutConstants.LOGO_LABEL_WIDTH + LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        titlePane.setBounds(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT);

        titlePane.setEditable(false);
        titlePane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".title { font-family: Arial; font-size: 11px; font-weight: bold; color: #ffffff; margin-top: 3px; }");
        titlePane.setEditorKit(kit);
        titlePane.setText("<div class='title'>TOEFL iBT Complete<br />Practice Test V25 Listening</div>");

        this.headerPanel.add(titlePane);
    }

    protected void setPauseTestButton() {
        this.pauseTestButton = new MButton();

        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - PAUSE_TEST_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        this.pauseTestButton.setBounds(x, y, PAUSE_TEST_BUTTON_WIDTH, PAUSE_TEST_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "pause_test.png"));
        this.pauseTestButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "pause_test_hi.png"));
        this.pauseTestButton.setRolloverIcon(rolloverIcon);
        this.pauseTestButton.setText(null);
        this.pauseTestButton.setMargin(new Insets(0, 0, 0, 0));
        this.pauseTestButton.setBorder(null);
        this.pauseTestButton.setBorderPainted(false);
        this.pauseTestButton.setFocusPainted(false);
        this.pauseTestButton.setContentAreaFilled(false);

        this.pauseTestButton.setActionCommand("doPauseTest");
        this.pauseTestButton.addActionListener(this);

        this.headerPanel.add(this.pauseTestButton);
    }

    protected void setSectionExitButton() {
        this.sectionExitButton = new MButton();

        int x = this.pauseTestButton.getX() + PAUSE_TEST_BUTTON_WIDTH + LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - SECTION_EXIT_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        this.sectionExitButton.setBounds(x, y, SECTION_EXIT_BUTTON_WIDTH, SECTION_EXIT_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "section_exit.png"));
        this.sectionExitButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "section_exit_hi.png"));
        this.sectionExitButton.setRolloverIcon(rolloverIcon);
        this.sectionExitButton.setText(null);
        this.sectionExitButton.setMargin(new Insets(0, 0, 0, 0));
        this.sectionExitButton.setBorder(null);
        this.sectionExitButton.setBorderPainted(false);
        this.sectionExitButton.setFocusPainted(false);
        this.sectionExitButton.setContentAreaFilled(false);

        this.sectionExitButton.setActionCommand("doSectionExit");
        this.sectionExitButton.addActionListener(this);

        this.headerPanel.add(this.sectionExitButton);
    }

    protected void setQuestionNumberPane() {
        this.questionNumberPane = new JEditorPane();

        int x = (this.headerPanel.getWidth() - QUESTION_NUMBER_PANE_WIDTH) / 2;
        int y = (this.headerPanel.getHeight() - QUESTION_NUMBER_PANE_HEIGHT) / 2;
        this.questionNumberPane.setBounds(x, y, QUESTION_NUMBER_PANE_WIDTH, QUESTION_NUMBER_PANE_HEIGHT);

        this.questionNumberPane.setEditable(false);
        this.questionNumberPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".question { font-family: Arial; font-size: 11px; font-weight: bold; color: #f5f5f5; text-align: center; }");
        this.questionNumberPane.setEditorKit(kit);
        this.questionNumberPane.setText("<div class='question'>Question 4 of 17</div>");

        this.headerPanel.add(this.questionNumberPane);
    }

    protected void setNextButton() {
        this.nextButton = new MButton();

        int x = this.headerPanel.getWidth() - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN;
        this.nextButton.setBounds(x, 0, NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "next.png"));
        this.nextButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "next_hi.png"));
        this.nextButton.setRolloverIcon(rolloverIcon);
        this.nextButton.setMargin(new Insets(0, 0, 0, 0));
        this.nextButton.setBorder(null);
        this.nextButton.setBorderPainted(false);
        this.nextButton.setFocusPainted(false);
        this.nextButton.setContentAreaFilled(false);

        this.nextButton.setActionCommand("doNext");
        this.nextButton.addActionListener(this);

        this.headerPanel.add(this.nextButton);
    }

    protected void setOkButton() {
        this.okButton = new MButton();

        int x = this.headerPanel.getWidth() - OK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 2;
        this.okButton.setBounds(x, 0, OK_BUTTON_WIDTH, OK_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "ok.png"));
        this.okButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "ok_hi.png"));
        this.okButton.setRolloverIcon(rolloverIcon);
        this.okButton.setMargin(new Insets(0, 0, 0, 0));
        this.okButton.setBorder(null);
        this.okButton.setBorderPainted(false);
        this.okButton.setFocusPainted(false);
        this.okButton.setContentAreaFilled(false);

        this.okButton.setActionCommand("doOk");
        this.okButton.addActionListener(this);

        this.headerPanel.add(this.okButton);
    }

    protected void setHelpButton() {
        this.helpButton = new MButton();

        int x = this.headerPanel.getWidth() - HELP_BUTTON_WIDTH - OK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 3;
        this.helpButton.setBounds(x, 0, HELP_BUTTON_WIDTH, HELP_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "help.png"));
        this.helpButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "help_hi.png"));
        this.helpButton.setRolloverIcon(rolloverIcon);
        this.helpButton.setMargin(new Insets(0, 0, 0, 0));
        this.helpButton.setBorder(null);
        this.helpButton.setBorderPainted(false);
        this.helpButton.setFocusPainted(false);
        this.helpButton.setContentAreaFilled(false);

        this.helpButton.setActionCommand("doHelp");
        this.helpButton.addActionListener(this);

        this.headerPanel.add(this.helpButton);
    }

    protected void setVolumeButton() {
        this.volumeButton = new MButton();

        int x = this.headerPanel.getWidth() - VOLUME_BUTTON_WIDTH - HELP_BUTTON_WIDTH - OK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 4;
        this.volumeButton.setBounds(x, 0, VOLUME_BUTTON_WIDTH, VOLUME_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "volume.png"));
        this.volumeButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "volume_hi.png"));
        this.volumeButton.setRolloverIcon(rolloverIcon);
        this.volumeButton.setMargin(new Insets(0, 0, 0, 0));
        this.volumeButton.setBorder(null);
        this.volumeButton.setBorderPainted(false);
        this.volumeButton.setFocusPainted(false);
        this.volumeButton.setContentAreaFilled(false);

        this.volumeButton.setActionCommand("doVolume");
        this.volumeButton.addActionListener(this);

        this.headerPanel.add(this.volumeButton);
    }

    protected void setContinueButton() {
        this.continueButton = new MButton();

        int x = this.headerPanel.getWidth() - CONTINUE_BUTTON_WIDTH - VOLUME_BUTTON_WIDTH - HELP_BUTTON_WIDTH - OK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 6;
        int y = LayoutConstants.MARGIN * 3;
        this.continueButton.setBounds(x, y, CONTINUE_BUTTON_WIDTH, CONTINUE_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "continue.png"));
        this.continueButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "continue_hi.png"));
        this.continueButton.setRolloverIcon(rolloverIcon);
        this.continueButton.setText(null);
        this.continueButton.setMargin(new Insets(0, 0, 0, 0));
        this.continueButton.setBorder(null);
        this.continueButton.setBorderPainted(false);
        this.continueButton.setFocusPainted(false);
        this.continueButton.setContentAreaFilled(false);

        this.continueButton.setActionCommand("doContinue");
        this.continueButton.addActionListener(this);

        this.headerPanel.add(this.continueButton);
    }

    protected void setTimerLabel() {
        this.timerLabel = new JLabel();

        int x = this.headerPanel.getWidth() - TIMER_LABEL_WIDTH - LayoutConstants.MARGIN * 2;
        int y = this.headerPanel.getHeight() - TIMER_LABEL_HEIGHT - LayoutConstants.MARGIN * 2;
        this.timerLabel.setBounds(x, y, TIMER_LABEL_WIDTH, TIMER_LABEL_HEIGHT);

        this.timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.timerLabel.setForeground(new Color(245, 245, 245));
        this.timerLabel.setText("00:07:35");

        this.headerPanel.add(this.timerLabel);
    }

    protected void setHideOrShowTimerButton() {
        this.hideOrShowTimerButton = new MButton();

        int x = this.headerPanel.getWidth() - HIDE_OR_SHOW_TIMER_BUTTON_WIDTH - TIMER_LABEL_WIDTH - LayoutConstants.MARGIN * 4;
        int y = this.headerPanel.getHeight() - HIDE_OR_SHOW_TIMER_BUTTON_HEIGHT - LayoutConstants.MARGIN * 2;
        this.hideOrShowTimerButton.setBounds(x, y, HIDE_OR_SHOW_TIMER_BUTTON_WIDTH, HIDE_OR_SHOW_TIMER_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "hide_timer.png"));
        this.hideOrShowTimerButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "hide_timer_hi.png"));
        this.hideOrShowTimerButton.setRolloverIcon(rolloverIcon);
        this.hideOrShowTimerButton.setText(null);
        this.hideOrShowTimerButton.setMargin(new Insets(0, 0, 0, 0));
        this.hideOrShowTimerButton.setBorder(null);
        this.hideOrShowTimerButton.setBorderPainted(false);
        this.hideOrShowTimerButton.setFocusPainted(false);
        this.hideOrShowTimerButton.setContentAreaFilled(false);

        this.hideOrShowTimerButton.setActionCommand("doHideOrShowTimer");
        this.hideOrShowTimerButton.addActionListener(this);

        this.headerPanel.add(this.hideOrShowTimerButton);
    }

    /**************************************************
     * Set Body Panel
     **************************************************/

    protected void setBodyPanel() {
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        this.bodyBounds = new Rectangle(0, LayoutConstants.HEADER_PANEL_HEIGHT, this.getWidth(), height);

        this.copyrightPanel = new CopyrightPanel(this.bodyBounds);
        this.bodyPanel = this.copyrightPanel;

        this.getContentPane().add(this.bodyPanel);
    }

    /**************************************************
     * Set Footer Panel
     **************************************************/

    protected void setFooterPanel() {
        this.footerPanel = new FooterPanel();

        this.footerPanel.setBounds(0, this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);

        this.footerPanel.setLayout(null);

        this.setCopyrightPane();

        this.getContentPane().add(footerPanel);
    }

    protected void setCopyrightPane() {
        JEditorPane copyrightPane = new JEditorPane();

        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        copyrightPane.setBounds(x, y, LayoutConstants.COPYRIGHT_PANE_WIDTH, LayoutConstants.COPYRIGHT_PANE_HEIGHT);

        copyrightPane.setEditable(false);
        copyrightPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".copyright { color: #ffffff; font-family: Arial; font-size: 8px; font-weight: bold; text-align: center; }");
        copyrightPane.setEditorKit(kit);
        copyrightPane.setText("<div class='copyright'>Copyright 2006, 2010, 2011 by Educational Testing Service. All rights reserved. EDUCATIONAL TESTING SERVICE, ETS, the ETS logo, TOEFL and TOEFL iBT are registered trademarks of Educational Testing Service (ETS) in the United States and other countries.</div>");

        this.footerPanel.add(copyrightPane);
    }

    /**************************************************
     * Listeners
     **************************************************/

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        switch (ac) {
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
                            rpPanel = new ReadingPassagePanel(bodyBounds, reading.getPassages().get(0));
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
                            lpPanel = new ListeningPassagePanel(bodyBounds, listening.getPassages().get(0));
                            bodyPanel = lpPanel;
                        } else if (bodyPanel instanceof ListeningPassagePanel) {
                            String hints = "Listen and answer.";
                            lhPanel = new ListeningHintsPanel(bodyBounds, hints);
                            bodyPanel = lhPanel;
                        } else if (bodyPanel instanceof ListeningHintsPanel) {
                            lqPanel = new ListeningQuestionPanel(bodyBounds, listening.getPassages().get(0).getQuestions().get(0));
                            bodyPanel = lqPanel;
                        }
                        getContentPane().add(bodyPanel);

                    }
                });
                break;
            case "doVolume":
                logger.info("'Volume' button pressed.");
                break;
            case "doHelp":
                logger.info("'Help' button pressed.");
                break;
            case "doOk":
                logger.info("'Ok' button pressed.");
                break;
            case "doNext":
                logger.info("'Next' button pressed.");
                break;
            default:
                break;
        }
    }

    /**************************************************
     * Getters and setters
     **************************************************/

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }
}
