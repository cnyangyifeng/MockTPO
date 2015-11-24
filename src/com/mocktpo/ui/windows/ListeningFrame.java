package com.mocktpo.ui.windows;

import com.mocktpo.MApplication;
import com.mocktpo.model.*;
import com.mocktpo.ui.dialogs.PauseTestDialog;
import com.mocktpo.ui.dialogs.SectionExitDialog;
import com.mocktpo.ui.tests.listening.*;
import com.mocktpo.util.GlobalConstants;
import com.thoughtworks.xstream.XStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.List;

public class ListeningFrame extends TestWithSectionControlFrame implements ActionListener {

    // Components

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

    @Override
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

    @Override
    protected void customizeBodyPanel() {
        this.headsetPanel = new HeadsetPanel(this.bodyBounds);
        this.bodyPanel = this.headsetPanel;
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
                        PauseTestDialog pause = new PauseTestDialog(ListeningFrame.this, "", true);
                        pause.setVisible(true);
                    }
                });
                break;
            case "doSectionExit":
                logger.info("'Section Exit' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        SectionExitDialog exit = new SectionExitDialog(ListeningFrame.this, "", true);
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
