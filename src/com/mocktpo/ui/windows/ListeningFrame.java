package com.mocktpo.ui.windows;

import com.mocktpo.MApplication;
import com.mocktpo.model.*;
import com.mocktpo.util.GlobalConstants;
import com.thoughtworks.xstream.XStream;

import java.awt.*;
import java.io.File;
import java.net.URL;

public class ListeningFrame extends TestFrame {

    // Variables

    private Rectangle bodyBounds;

    private String testIndex;
    private MListening listening;
    private int nextQuestion = 0;

    public ListeningFrame(GraphicsConfiguration gc, MainFrame mainFrame) {
        super(gc, mainFrame);
        initComponents();
    }

    private void initComponents() {
        this.globalSettings();
        this.setLayout(null);
    }

    private void globalSettings() {
        this.configData();
    }

    private void configData() {
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

}
