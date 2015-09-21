package com.mocktpo.ui.windows;

import com.mocktpo.model.ChoiceOption;
import com.mocktpo.model.ChoiceQuestion;
import com.mocktpo.model.ChoiceQuestionGroup;
import com.mocktpo.ui.dialogs.PauseDialog;
import com.mocktpo.ui.tests.listening.*;
import com.mocktpo.ui.widgets.*;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import com.thoughtworks.xstream.XStream;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class TestFrame extends JFrame implements ActionListener {

    public static final int PAUSE_TEST_BUTTON_WIDTH = 84;
    public static final int PAUSE_TEST_BUTTON_HEIGHT = 34;

    public static final int SECTION_EXIT_BUTTON_WIDTH = 84;
    public static final int SECTION_EXIT_BUTTON_HEIGHT = 34;

    public static final int QUESTION_NUMBER_PANE_WIDTH = 200;
    public static final int QUESTION_NUMBER_PANE_HEIGHT = 20;

    public static final int NEXT_BUTTON_WIDTH = 70;
    public static final int NEXT_BUTTON_HEIGHT = 50;

    public static final int OK_BUTTON_WIDTH = 70;
    public static final int OK_BUTTON_HEIGHT = 50;

    public static final int HELP_BUTTON_WIDTH = 70;
    public static final int HELP_BUTTON_HEIGHT = 50;

    public static final int VOLUME_BUTTON_WIDTH = 70;
    public static final int VOLUME_BUTTON_HEIGHT = 50;

    public static final int CONTINUE_BUTTON_WIDTH = 74;
    public static final int CONTINUE_BUTTON_HEIGHT = 34;

    public static final int TIMER_LABEL_WIDTH = 60;
    public static final int TIMER_LABEL_HEIGHT = 20;

    public static final int HIDE_OR_SHOW_TIMER_BUTTON_WIDTH = 70;
    public static final int HIDE_OR_SHOW_TIMER_BUTTON_HEIGHT = 50;

    /**************************************************
     * Properties
     **************************************************/

    private MainFrame mainFrame;
    private HeaderPanel headerPanel;
    private JLabel logoLabel;
    private JEditorPane titlePane;
    private MButton pauseTestButton;
    private MButton sectionExitButton;

    private JEditorPane questionNumberPane;

    private MOvalButton nextButton;
    private MOvalButton okButton;
    private MOvalButton helpButton;
    private MOvalButton volumeButton;
    private MButton continueButton;

    private JLabel timerLabel;
    private MButton hideOrShowTimerButton;

    private BodyPanel bodyPanel;
    private HeadsetPanel headsetPanel;
    private ChangingVolumePanel cvPanel;
    private ListeningSectionDirectionsPanel lsdPanel;
    private ConversationPanel conversationPanel;
    private ListeningHintsPanel lhPanel;
    private ConversationQuestionPanel cqPanel;
    private FooterPanel footerPanel;
    private JEditorPane copyrightPane;

    private Rectangle bodyBounds;

    private String testIndex;

    public TestFrame(GraphicsConfiguration gc, MainFrame mainFrame, String testIndex) {
        super(gc);
        this.mainFrame = mainFrame;
        this.testIndex = testIndex;
        initComponents();
    }

    private void initComponents() {
        this.globalSettings();

        this.setLayout(null);

        this.setHeaderPanel();
        this.setBodyPanel();
        this.setFooterPanel();

        this.getContentPane().add(headerPanel);
        this.getContentPane().add(bodyPanel);
        this.getContentPane().add(footerPanel);
    }

    private void globalSettings() {
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
     * Set Header Panel
     **************************************************/

    private void setHeaderPanel() {
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

        this.headerPanel.add(this.logoLabel);
        this.headerPanel.add(this.titlePane);
        this.headerPanel.add(this.pauseTestButton);
        this.headerPanel.add(this.sectionExitButton);

        this.headerPanel.add(this.questionNumberPane);

        this.headerPanel.add(this.nextButton);
        this.headerPanel.add(this.okButton);
        this.headerPanel.add(this.helpButton);
        this.headerPanel.add(this.volumeButton);
        this.headerPanel.add(this.continueButton);

        this.headerPanel.add(this.timerLabel);
    }

    private void setLogoLabel() {
        this.logoLabel = new JLabel();

        this.logoLabel.setBounds(0, LayoutConstants.MARGIN, LayoutConstants.LOGO_LABEL_WIDTH, LayoutConstants.LOGO_LABEL_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "logo.png"));
        this.logoLabel.setIcon(icon);
    }

    private void setTitlePane() {
        this.titlePane = new JEditorPane();

        int x = LayoutConstants.LOGO_LABEL_WIDTH + LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        this.titlePane.setBounds(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT);

        this.titlePane.setEditable(false);
        this.titlePane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".title { font-family: Arial; font-size: 11px; font-weight: bold; color: #ffffff; margin-top: 3px; }");
        this.titlePane.setEditorKit(kit);
        this.titlePane.setText("<div class='title'>TOEFL iBT Complete<br />Practice Test V25 Listening</div>");
    }

    private void setPauseTestButton() {
        this.pauseTestButton = new MButton();

        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - PAUSE_TEST_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        this.pauseTestButton.setBounds(x, y, PAUSE_TEST_BUTTON_WIDTH, PAUSE_TEST_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "pause_test.png"));
        this.pauseTestButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "pause_test_hi.png"));
        this.pauseTestButton.setRolloverIcon(rolloverIcon);
        this.pauseTestButton.setText(null);
        this.pauseTestButton.setMargin(new Insets(0, 0, 0, 0));
        this.pauseTestButton.setBorder(null);
        this.pauseTestButton.setBorderPainted(false);
        this.pauseTestButton.setFocusPainted(false);
        this.pauseTestButton.setContentAreaFilled(false);

        this.pauseTestButton.setActionCommand("doPauseTest");
        this.pauseTestButton.addActionListener(this);
    }

    private void setSectionExitButton() {
        this.sectionExitButton = new MButton();

        int x = this.pauseTestButton.getX() + PAUSE_TEST_BUTTON_WIDTH + LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - SECTION_EXIT_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        this.sectionExitButton.setBounds(x, y, SECTION_EXIT_BUTTON_WIDTH, SECTION_EXIT_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "section_exit.png"));
        this.sectionExitButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "section_exit_hi.png"));
        this.sectionExitButton.setRolloverIcon(rolloverIcon);
        this.sectionExitButton.setText(null);
        this.sectionExitButton.setMargin(new Insets(0, 0, 0, 0));
        this.sectionExitButton.setBorder(null);
        this.sectionExitButton.setBorderPainted(false);
        this.sectionExitButton.setFocusPainted(false);
        this.sectionExitButton.setContentAreaFilled(false);

        this.sectionExitButton.setActionCommand("doSectionExit");
        this.sectionExitButton.addActionListener(this);
    }

    private void setQuestionNumberPane() {
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
    }

    private void setNextButton() {
        this.nextButton = new MOvalButton(" NEXT ");

        int x = this.headerPanel.getWidth() - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN;
        this.nextButton.setBounds(x, 0, NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT);

        this.nextButton.setMargin(new Insets(0, 0, 0, 0));
        this.nextButton.setBorder(null);
        this.nextButton.setBorderPainted(false);
        this.nextButton.setFocusPainted(false);
        this.nextButton.setContentAreaFilled(false);
    }

    private void setOkButton() {
        this.okButton = new MOvalButton("  OK  ");

        int x = this.headerPanel.getWidth() - OK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 2;
        this.okButton.setBounds(x, 0, OK_BUTTON_WIDTH, OK_BUTTON_HEIGHT);

        this.okButton.setMargin(new Insets(0, 0, 0, 0));
        this.okButton.setBorder(null);
        this.okButton.setBorderPainted(false);
        this.okButton.setFocusPainted(false);
        this.okButton.setContentAreaFilled(false);
    }

    private void setHelpButton() {
        this.helpButton = new MOvalButton(" HELP ");

        int x = this.headerPanel.getWidth() - HELP_BUTTON_WIDTH - OK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 3;
        this.helpButton.setBounds(x, 0, HELP_BUTTON_WIDTH, HELP_BUTTON_HEIGHT);

        this.helpButton.setMargin(new Insets(0, 0, 0, 0));
        this.helpButton.setBorder(null);
        this.helpButton.setBorderPainted(false);
        this.helpButton.setFocusPainted(false);
        this.helpButton.setContentAreaFilled(false);
    }

    private void setVolumeButton() {
        this.volumeButton = new MOvalButton("VOLUME");

        int x = this.headerPanel.getWidth() - VOLUME_BUTTON_WIDTH - HELP_BUTTON_WIDTH - OK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 4;
        this.volumeButton.setBounds(x, 0, VOLUME_BUTTON_WIDTH, VOLUME_BUTTON_HEIGHT);

        this.volumeButton.setMargin(new Insets(0, 0, 0, 0));
        this.volumeButton.setBorder(null);
        this.volumeButton.setBorderPainted(false);
        this.volumeButton.setFocusPainted(false);
        this.volumeButton.setContentAreaFilled(false);
    }

    private void setContinueButton() {
        this.continueButton = new MButton();

        int x = this.headerPanel.getWidth() - CONTINUE_BUTTON_WIDTH - VOLUME_BUTTON_WIDTH - HELP_BUTTON_WIDTH - OK_BUTTON_WIDTH - NEXT_BUTTON_WIDTH - LayoutConstants.MARGIN * 6;
        int y = LayoutConstants.MARGIN * 3;
        this.continueButton.setBounds(x, y, CONTINUE_BUTTON_WIDTH, CONTINUE_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "continue.png"));
        this.continueButton.setIcon(icon);
        this.continueButton.setText(null);
        this.continueButton.setMargin(new Insets(0, 0, 0, 0));
        this.continueButton.setBorder(null);
        this.continueButton.setBorderPainted(false);
        this.continueButton.setFocusPainted(false);
        this.continueButton.setContentAreaFilled(false);

        this.continueButton.setActionCommand("doContinue");
        this.continueButton.addActionListener(this);
    }

    private void setTimerLabel() {
        this.timerLabel = new JLabel();

        int x = this.headerPanel.getWidth() - TIMER_LABEL_WIDTH - LayoutConstants.MARGIN * 2;
        int y = this.headerPanel.getHeight() - TIMER_LABEL_HEIGHT - LayoutConstants.MARGIN * 2;
        this.timerLabel.setBounds(x, y, TIMER_LABEL_WIDTH, TIMER_LABEL_HEIGHT);

        this.timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.timerLabel.setForeground(new Color(245, 245, 245));
        this.timerLabel.setText("00:07:35");
    }

    /**************************************************
     * Set Body Panel
     **************************************************/

    private void setBodyPanel() {
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        this.bodyBounds = new Rectangle(0, LayoutConstants.HEADER_PANEL_HEIGHT, this.getWidth(), height);
        this.bodyPanel = new BodyPanel(this.bodyBounds);
        this.headsetPanel = new HeadsetPanel(this.bodyBounds);
        this.bodyPanel = this.headsetPanel;
    }

    /**************************************************
     * Set Footer Panel
     **************************************************/

    private void setFooterPanel() {
        this.footerPanel = new FooterPanel();

        this.footerPanel.setBounds(0, this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);

        this.footerPanel.setLayout(null);

        this.setCopyrightPane();

        this.footerPanel.add(this.copyrightPane);
    }

    private void setCopyrightPane() {
        this.copyrightPane = new JEditorPane();

        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        this.copyrightPane.setBounds(x, y, LayoutConstants.COPYRIGHT_PANE_WIDTH, LayoutConstants.COPYRIGHT_PANE_HEIGHT);

        this.copyrightPane.setEditable(false);
        this.copyrightPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".copyright { color: #ffffff; font-family: Arial; font-size: 8px; font-weight: bold; text-align: center; }");
        this.copyrightPane.setEditorKit(kit);
        this.copyrightPane.setText("<div class='copyright'>Copyright 2006, 2010, 2011 by Educational Testing Service. All rights reserved. EDUCATIONAL TESTING SERVICE, ETS, the ETS logo, TOEFL and TOEFL iBT are registered trademarks of Educational Testing Service (ETS) in the United States and other countries.</div>");
    }

    /**************************************************
     * Public methods
     **************************************************/

    public void didPauseTest() {
        if (bodyPanel instanceof HeadsetPanel) {

        } else if (bodyPanel instanceof ListeningSectionDirectionsPanel) {
            this.lsdPanel.stopAudio();
        } else if (bodyPanel instanceof ConversationPanel) {
            this.conversationPanel.stopAudio();
        }
    }

    /**************************************************
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        switch (ac) {
            case "doPauseTest":
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        PauseDialog pause = new PauseDialog(TestFrame.this, "", true);
                        pause.setVisible(true);
                    }
                });
                break;
            case "doSectionExit":
                break;
            case "doContinue":
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        getContentPane().remove(bodyPanel);
                        if (bodyPanel instanceof HeadsetPanel) {
                            cvPanel = new ChangingVolumePanel(bodyBounds);
                            bodyPanel = cvPanel;
                        } else if (bodyPanel instanceof ChangingVolumePanel) {
                            lsdPanel = new ListeningSectionDirectionsPanel(bodyBounds);
                            bodyPanel = lsdPanel;
                            lsdPanel.startAudio();
                        } else if (bodyPanel instanceof ListeningSectionDirectionsPanel) {
                            lsdPanel.stopAudio();
                            String imageVal = GlobalConstants.TESTS_ROOT + testIndex + GlobalConstants.LISTENING_DIR + "L1-C1.png";
                            URL imageUrl = this.getClass().getResource(imageVal);
                            String audioVal = GlobalConstants.TESTS_ROOT + testIndex + GlobalConstants.LISTENING_DIR + "L1-C1.mp3";
                            URL audioUrl = this.getClass().getResource(audioVal);
                            conversationPanel = new ConversationPanel(bodyBounds, imageUrl, audioUrl);
                            bodyPanel = conversationPanel;
                            conversationPanel.startAudio();
                        } else if (bodyPanel instanceof ConversationPanel) {
                            conversationPanel.stopAudio();
                            lhPanel = new ListeningHintsPanel(bodyBounds, "Now get ready to answer the questions. You may use your notes to help you answer.");
                            bodyPanel = lhPanel;
                        } else if (bodyPanel instanceof ListeningHintsPanel) {
                            conversationPanel.stopAudio();
                            ChoiceQuestionGroup cqg;
                            try {
                                XStream xs = new XStream();
                                xs.alias("question-group", ChoiceQuestionGroup.class);
                                xs.alias("question", ChoiceQuestion.class);
                                xs.alias("option", ChoiceOption.class);
                                String val = GlobalConstants.TESTS_ROOT + testIndex + GlobalConstants.LISTENING_DIR + "L1-C1-Q.xml";
                                URL xml = this.getClass().getResource(val);
                                cqg = (ChoiceQuestionGroup) xs.fromXML(new File(xml.toURI()));
                                ChoiceQuestion cq = cqg.getQuestions().get(0);
                                String audioVal = GlobalConstants.TESTS_ROOT + testIndex + GlobalConstants.LISTENING_DIR + cq.getAudio();
                                URL audioUrl = this.getClass().getResource(audioVal);

                                cqPanel = new ConversationQuestionPanel(bodyBounds, cq, audioUrl);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            bodyPanel = cqPanel;
                            cqPanel.startAudio();
                        }
                        getContentPane().add(bodyPanel);
                        repaint();
                    }
                });
                break;
            default:
                break;
        }
    }

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }
}
