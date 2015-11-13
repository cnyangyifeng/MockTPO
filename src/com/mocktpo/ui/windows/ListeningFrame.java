package com.mocktpo.ui.windows;

import com.mocktpo.MApplication;
import com.mocktpo.model.*;
import com.mocktpo.ui.dialogs.PauseDialog;
import com.mocktpo.ui.tests.listening.*;
import com.mocktpo.ui.widgets.MButton;
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
import java.util.List;

public class ListeningFrame extends TestFrame implements ActionListener {

    // Constants

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

    // Components

    private JEditorPane questionNumberPane;
    private MButton nextButton;
    private MButton okButton;
    private MButton helpButton;
    private MButton volumeButton;
    private MButton continueButton;
    private JLabel timerLabel;
    private MButton hideOrShowTimerButton;

    private HeadsetPanel headsetPanel;
    private ChangingVolumePanel cvPanel;
    private ListeningDirectionsPanel ldPanel;
    private ListeningPassagePanel lpPanel;
    private ListeningHintsPanel lhPanel;
    private ListeningQuestionPanel lqPanel;

    // Variables

    private MListening listening;
    private int nextQuestion = 0;

    public ListeningFrame(GraphicsConfiguration gc, MainFrame mainFrame) {
        super(gc, mainFrame);
    }

    protected void configData() {
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

    protected void customizeHeaderPanel() {
        this.setQuestionNumberPane();
        this.setControlButtons();
        this.setTimerLabel();
        this.setHideOrShowTimerButton();
    }

    protected void customizeBodyPanel() {
        this.headsetPanel = new HeadsetPanel(this.bodyBounds);
        this.bodyPanel = this.headsetPanel;
    }

    /**************************************************
     * Private methods
     **************************************************/

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

        this.headerPanel.add(this.questionNumberPane);
    }

    private void setControlButtons() {
        this.setNextButton();
        this.setOkButton();
        this.setHelpButton();
        this.setVolumeButton();
        this.setContinueButton();
    }

    private void setNextButton() {
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

        // this.nextButton.setEnabled(false);

        this.nextButton.setActionCommand("doNext");
        this.nextButton.addActionListener(this);

        this.headerPanel.add(this.nextButton);
    }

    private void setOkButton() {
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

        // this.okButton.setEnabled(false);

        this.okButton.setActionCommand("doOk");
        this.okButton.addActionListener(this);

        this.headerPanel.add(this.okButton);
    }

    private void setHelpButton() {
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

        // this.helpButton.setEnabled(false);

        this.helpButton.setActionCommand("doHelp");
        this.helpButton.addActionListener(this);

        this.headerPanel.add(this.helpButton);
    }

    private void setVolumeButton() {
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

    private void setContinueButton() {
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

    private void setTimerLabel() {
        this.timerLabel = new JLabel();

        int x = this.headerPanel.getWidth() - TIMER_LABEL_WIDTH - LayoutConstants.MARGIN * 2;
        int y = this.headerPanel.getHeight() - TIMER_LABEL_HEIGHT - LayoutConstants.MARGIN * 2;
        this.timerLabel.setBounds(x, y, TIMER_LABEL_WIDTH, TIMER_LABEL_HEIGHT);

        this.timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.timerLabel.setForeground(new Color(245, 245, 245));
        this.timerLabel.setText("00:07:35");

        this.headerPanel.add(this.timerLabel);
    }

    private void setHideOrShowTimerButton() {
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
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        switch (ac) {
            case "doPauseTest":
                logger.info("'Pause Test' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        PauseDialog pause = new PauseDialog(ListeningFrame.this, "", true);
                        pause.setVisible(true);
                    }
                });
                break;
            case "doSectionExit":
                logger.info("'Section Exit' button pressed.");
                break;
            case "doContinue":
                logger.info("'Continue' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        getContentPane().remove(bodyPanel);
                        if (bodyPanel instanceof HeadsetPanel) {
                            cvPanel = new ChangingVolumePanel(bodyBounds);
                            bodyPanel = cvPanel;
                        } else if (bodyPanel instanceof ChangingVolumePanel) {
                            ldPanel = new ListeningDirectionsPanel(bodyBounds);
                            bodyPanel = ldPanel;
                            ldPanel.startAudio();
                        } else if (bodyPanel instanceof ListeningDirectionsPanel) {
                            ldPanel.stopAudio();
                            MListeningPassage passage = listening.getPassages().get(0);
                            lpPanel = new ListeningPassagePanel(bodyBounds, passage);
                            bodyPanel = lpPanel;
                            lpPanel.startAudio();
                        } else if (bodyPanel instanceof ListeningPassagePanel) {
                            String hints = "Now get ready to answer the questions. You may use your notes to help you answer.";
                            lhPanel = new ListeningHintsPanel(bodyBounds, hints);
                            bodyPanel = lhPanel;
                        } else if (bodyPanel instanceof ListeningHintsPanel) {
                            List<MListeningQuestion> questions = listening.getPassages().get(0).getQuestions();
                            if (nextQuestion < questions.size()) {
                                MListeningQuestion lq = questions.get(nextQuestion++);
                                lqPanel = new ListeningQuestionPanel(bodyBounds, lq);
                            }
                            bodyPanel = lqPanel;
                            lqPanel.startAudio();
                        } else if (bodyPanel instanceof ListeningQuestionPanel) {
                            lqPanel.stopAudio();
                            List<MListeningQuestion> questions = listening.getPassages().get(0).getQuestions();
                            if (nextQuestion < questions.size()) {
                                MListeningQuestion lq = questions.get(nextQuestion++);
                                lqPanel = new ListeningQuestionPanel(bodyBounds, lq);
                            }
                            bodyPanel = lqPanel;
                            lqPanel.startAudio();
                        }
                        getContentPane().add(bodyPanel);
                        repaint();
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
}
