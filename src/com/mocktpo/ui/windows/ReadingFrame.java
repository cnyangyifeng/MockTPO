package com.mocktpo.ui.windows;

import com.mocktpo.MApplication;
import com.mocktpo.model.*;
import com.mocktpo.ui.dialogs.PauseTestDialog;
import com.mocktpo.ui.dialogs.SectionExitDialog;
import com.mocktpo.ui.tests.reading.ReadingDirectionsPanel;
import com.mocktpo.ui.tests.reading.ReadingPassagePanel;
import com.mocktpo.util.GlobalConstants;
import com.thoughtworks.xstream.XStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class ReadingFrame extends TestWithSectionControlFrame implements ActionListener {

    // Components

    private ReadingDirectionsPanel rdPanel;
    private ReadingPassagePanel rpPanel;

    // Variables

    private MReading reading;

    public ReadingFrame(GraphicsConfiguration gc, MainFrame mainFrame) {
        super(gc, mainFrame);
    }

    @Override
    protected void configData() {
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

    @Override
    protected void customizeBodyPanel() {
        this.rdPanel = new ReadingDirectionsPanel(this.bodyBounds);
        this.bodyPanel = this.rdPanel;
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
                        PauseTestDialog pause = new PauseTestDialog(ReadingFrame.this, "", true);
                        pause.setVisible(true);
                    }
                });
                break;
            case "doSectionExit":
                logger.info("'Section Exit' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        SectionExitDialog exit = new SectionExitDialog(ReadingFrame.this, "", true);
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
                        if (bodyPanel instanceof ReadingDirectionsPanel) {
                            MReadingPassage passage = reading.getPassages().get(0);
                            rpPanel = new ReadingPassagePanel(bodyBounds, passage);
                            bodyPanel = rpPanel;
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
