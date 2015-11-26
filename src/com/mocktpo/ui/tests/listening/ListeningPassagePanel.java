package com.mocktpo.ui.tests.listening;

import com.mocktpo.MApplication;
import com.mocktpo.model.MListeningPassage;
import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;

import javax.media.*;
import javax.media.format.AudioFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListeningPassagePanel extends BodyPanel implements ActionListener {

    public static final int CONVERSATION_LABEL_WIDTH = 600;
    public static final int CONVERSATION_LABEL_HEIGHT = 450;

    public static final int CONVERSATION_PROGRESS_BAR_WIDTH = 320;
    public static final int CONVERSATION_PROGRESS_BAR_HEIGHT = 6;

    private JLabel conversationLabel;
    private JProgressBar conversationProgressBar;
    private Player audioPlayer;
    private Timer timer;

    private MListeningPassage passage;

    public ListeningPassagePanel(Rectangle bounds, MListeningPassage passage) {
        super(bounds);
        this.passage = passage;
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setConversationLabel();
        this.setConversationProgressBar();

        this.setAudioPlayer();
    }

    protected void setConversationLabel() {
        this.conversationLabel = new JLabel();

        int x = (this.getWidth() - CONVERSATION_LABEL_WIDTH) / 2;
        this.conversationLabel.setBounds(x, LayoutConstants.MARGIN * 5, CONVERSATION_LABEL_WIDTH, CONVERSATION_LABEL_HEIGHT);

        String imageVal = GlobalConstants.TESTS_DIR + MApplication.settings.get("testIndex") + GlobalConstants.LISTENING_DIR + this.passage.getImages().get(0).getIndex();
        ImageIcon icon = new ImageIcon(this.getClass().getResource(imageVal));
        this.conversationLabel.setIcon(icon);

        this.add(this.conversationLabel);
    }

    protected void setConversationProgressBar() {
        this.conversationProgressBar = new JProgressBar();

        int x = (this.getWidth() - CONVERSATION_PROGRESS_BAR_WIDTH) / 2;
        int y = this.conversationLabel.getY() + CONVERSATION_LABEL_HEIGHT + LayoutConstants.MARGIN * 10;
        this.conversationProgressBar.setBounds(x, y, CONVERSATION_PROGRESS_BAR_WIDTH, CONVERSATION_PROGRESS_BAR_HEIGHT);

        this.conversationProgressBar.setMinimum(0);
        this.conversationProgressBar.setMaximum(100);
        this.conversationProgressBar.setValue(0);
        this.conversationProgressBar.setStringPainted(false);
        this.conversationProgressBar.setIndeterminate(false);

        this.add(this.conversationProgressBar);
    }

    protected void setAudioPlayer() {
        Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
        Format input2 = new AudioFormat(AudioFormat.MPEG);
        Format output = new AudioFormat(AudioFormat.LINEAR);
        PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]{input1, input2}, new Format[]{output}, PlugInManager.CODEC);
        try {
            String audioVal = GlobalConstants.TESTS_DIR + MApplication.settings.get("testIndex") + GlobalConstants.LISTENING_DIR + this.passage.getAudios().get(0).getIndex();
            this.audioPlayer = Manager.createRealizedPlayer(new MediaLocator(this.getClass().getResource(audioVal)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startAudio() {
        this.audioPlayer.start();
        this.timer = new Timer(1000, this);
        this.timer.setActionCommand("doStartAudio");
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
        if ("doStartAudio".equals(e.getActionCommand())) {
            long duration = this.audioPlayer.getDuration().getNanoseconds();
            long now = this.audioPlayer.getMediaTime().getNanoseconds();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    if (now < duration) {
                        int progress = (int) ((now * 100) / duration);
                        if (progress < 95) {
                            // 95% might bring bugs.
                            conversationProgressBar.setValue(progress);
                        } else {
                            conversationProgressBar.setValue(100);
                            timer.stop();
                        }
                    } else {
                        conversationProgressBar.setValue(100);
                        timer.stop();
                    }
                }
            });
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
        return false;
    }

    @Override
    public boolean nextButtonAvailable() {
        return true;
    }

    @Override
    public boolean okButtonAvailable() {
        return true;
    }

    @Override
    public boolean helpButtonAvailable() {
        return true;
    }

    @Override
    public boolean volumeButtonAvailable() {
        return true;
    }

    @Override
    public boolean continueButtonAvailable() {
        return false;
    }

    @Override
    public boolean timerLabelAvailable() {
        return false;
    }

    @Override
    public boolean hideOrShowTimerButtonAvailable() {
        return false;
    }

    /**************************************************
     * Control Buttons Status - Enabled
     **************************************************/

    @Override
    public boolean nextButtonEnabled() {
        return false;
    }

    @Override
    public boolean okButtonEnabled() {
        return false;
    }

    @Override
    public boolean helpButtonEnabled() {
        return false;
    }

    @Override
    public boolean volumeButtonEnabled() {
        return true;
    }
}
