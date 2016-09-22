package com.mocktpo.view.tests.listening;

import com.mocktpo.MyApplication;
import com.mocktpo.model.MListeningPassage;
import com.mocktpo.view.widgets.BodyPanel;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;

import javax.media.*;
import javax.media.format.AudioFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListeningPassagePanel extends BodyPanel implements ActionListener {

    /* Constants */

    public static final int CONVERSATION_LABEL_WIDTH = 600;
    public static final int CONVERSATION_LABEL_HEIGHT = 450;

    public static final int CONVERSATION_PROGRESS_BAR_WIDTH = 320;
    public static final int CONVERSATION_PROGRESS_BAR_HEIGHT = 6;

    /* Components */

    protected JLabel conversationLabel;
    protected JProgressBar conversationProgressBar;

    /* Variables */

    protected MListeningPassage passage;
    private Player audioPlayer;
    private Timer timer;

    /**************************************************
     * Constructors
     **************************************************/

    public ListeningPassagePanel(Rectangle bounds, MListeningPassage passage) {
        super(bounds);
        this.passage = passage;
        this.initComponents();
    }

    /**************************************************
     * Components Initialization
     **************************************************/

    private void initComponents() {
        /* Set layout */
        this.setLayout(null);
        /* Set components */
        this.setConversationLabel();
        this.setConversationProgressBar();
        /* Set audio player */
        this.setAudioPlayer();
    }

    protected void setConversationLabel() {
        this.conversationLabel = new JLabel();

        int x = (this.getWidth() - CONVERSATION_LABEL_WIDTH) / 2;
        this.conversationLabel.setBounds(x, LayoutConstants.MARGIN * 5, CONVERSATION_LABEL_WIDTH, CONVERSATION_LABEL_HEIGHT);

        //String imageVal = GlobalConstants.TESTS_DIR + MyApplication.settings.get("testIndex") + GlobalConstants.LISTENING_DIR + this.passage.getImages().get(0).getIndex();
        //ImageIcon icon = new ImageIcon(this.getClass().getResource(imageVal));
        //this.conversationLabel.setIcon(icon);

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

    /**************************************************
     * Audio Player
     **************************************************/

    protected void setAudioPlayer() {
        Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
        Format input2 = new AudioFormat(AudioFormat.MPEG);
        Format output = new AudioFormat(AudioFormat.LINEAR);
        PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]{input1, input2}, new Format[]{output}, PlugInManager.CODEC);
        try {
            //String audioVal = GlobalConstants.TESTS_DIR + MyApplication.settings.get("testIndex") + GlobalConstants.LISTENING_DIR + this.passage.getAudios().get(0).getIndex();
            //this.audioPlayer = Manager.createRealizedPlayer(new MediaLocator(this.getClass().getResource(audioVal)));
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
        String ac = e.getActionCommand();
        switch (ac) {
            case "doStartAudio":
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
                break;
            default:
                break;
        }
    }
}
