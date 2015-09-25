package com.mocktpo.ui.tests.listening;

import com.mocktpo.MApplication;
import com.mocktpo.model.MChoiceOption;
import com.mocktpo.model.MListeningQuestion;
import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.media.*;
import javax.media.format.AudioFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class ListeningQuestionPanel extends BodyPanel implements ActionListener, ItemListener {

    private static final Logger logger = LogManager.getLogger();

    public static final int SUBJECT_LABEL_WIDTH = 800;
    public static final int SUBJECT_LABEL_HEIGHT = 80;

    public static final int OPTIONS_PANEL_WIDTH = 800;
    public static final int OPTIONS_PANEL_HEIGHT = 200;

    public static final int OPTION_BUTTON_WIDTH = 800;
    public static final int OPTION_BUTTON_HEIGHT = 40;

    private JLabel subjectLabel;
    private JPanel optionsPanel;
    private Player audioPlayer;
    private Timer timer;

    private MListeningQuestion question;

    public ListeningQuestionPanel(Rectangle bounds, MListeningQuestion question) {
        super(bounds);
        this.question = question;
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setSubjectLabel();
        this.setOptionsPanel();

        this.add(this.subjectLabel);
        this.add(this.optionsPanel);

        this.setAudioPlayer();
    }

    private void setSubjectLabel() {
        this.subjectLabel = new JLabel();

        int x = (this.getWidth() - SUBJECT_LABEL_WIDTH) / 2;
        int y = (this.getHeight() - SUBJECT_LABEL_HEIGHT - OPTIONS_PANEL_HEIGHT) / 2;
        this.subjectLabel.setBounds(x, y, SUBJECT_LABEL_WIDTH, SUBJECT_LABEL_HEIGHT);

        this.subjectLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.subjectLabel.setForeground(new Color(51, 51, 51));
        this.subjectLabel.setText(this.question.getSubject());
    }

    private void setOptionsPanel() {
        this.optionsPanel = new JPanel();
        this.optionsPanel.setLayout(null);

        int x = (this.getWidth() - OPTIONS_PANEL_WIDTH) / 2;
        int y = (this.getHeight() - SUBJECT_LABEL_HEIGHT - OPTIONS_PANEL_HEIGHT) / 2 + SUBJECT_LABEL_HEIGHT;
        this.optionsPanel.setBounds(x, y, OPTIONS_PANEL_WIDTH, OPTIONS_PANEL_HEIGHT);

        this.optionsPanel.setBackground(new Color(255, 255, 255));

        List<MChoiceOption> options = this.question.getOptions();
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < options.size(); i++) {
            MChoiceOption option = options.get(i);

            JRadioButton radioButton = new JRadioButton(option.getText());
            radioButton.setBounds(0, OPTION_BUTTON_HEIGHT * i + LayoutConstants.MARGIN * i * 2, OPTION_BUTTON_WIDTH, OPTION_BUTTON_HEIGHT);

            radioButton.setFont(new Font("Arial", Font.PLAIN, 16));
            radioButton.setForeground(new Color(51, 51, 51));
            radioButton.setName(option.getIndex());
            ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "unchecked.png"));
            radioButton.setIcon(icon);
            ImageIcon selectedIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "checked.png"));
            radioButton.setSelectedIcon(selectedIcon);
            radioButton.addItemListener(this);

            buttonGroup.add(radioButton);

            this.optionsPanel.add(radioButton);
        }
    }

    private void setAudioPlayer() {
        Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
        Format input2 = new AudioFormat(AudioFormat.MPEG);
        Format output = new AudioFormat(AudioFormat.LINEAR);
        PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]{input1, input2}, new Format[]{output}, PlugInManager.CODEC);
        try {
            String audioVal = GlobalConstants.TESTS_ROOT + MApplication.settings.get("testIndex") + GlobalConstants.LISTENING_DIR + question.getAudio().getIndex();
            this.audioPlayer = Manager.createRealizedPlayer(new MediaLocator(this.getClass().getResource(audioVal)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startAudio() {
        this.audioPlayer.start();
        this.timer = new Timer(1000, this);
        this.timer.setActionCommand("startAudio");
        this.timer.start();
    }

    public void stopAudio() {
        this.audioPlayer.stop();
        this.timer.stop();
    }

    /**************************************************
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        if ("startAudio".equals(e.getActionCommand())) {
            long duration = this.audioPlayer.getDuration().getNanoseconds();
            long now = this.audioPlayer.getMediaTime().getNanoseconds();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    if (now >= duration) {
                        timer.stop();
                    }
                }
            });
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JRadioButton radioButton = (JRadioButton) e.getSource();
            logger.info("{}: {} answer: {}, user choice: {}.", this.question.getIndex(), this.question.getSubject(), this.question.getAnswer(), radioButton.getName());
        }
    }
}