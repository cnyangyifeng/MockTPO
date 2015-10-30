package com.mocktpo.ui.tests.listening;

import com.mocktpo.MApplication;
import com.mocktpo.model.MListeningSection;
import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;

import javax.media.*;
import javax.media.format.AudioFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListeningSectionPanel extends BodyPanel implements ActionListener {

    public static final int CONVERSATION_LABEL_WIDTH = 600;
    public static final int CONVERSATION_LABEL_HEIGHT = 450;

    public static final int CONVERSATION_PROGRESS_BAR_WIDTH = 320;
    public static final int CONVERSATION_PROGRESS_BAR_HEIGHT = 6;

    private JLabel conversationLabel;
    private JProgressBar conversationProgressBar;
    private Player audioPlayer;
    private Timer timer;

    private MListeningSection listeningSection;

    public ListeningSectionPanel(Rectangle bounds, MListeningSection listeningSection) {
        super(bounds);
        this.listeningSection = listeningSection;
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setConversationLabel();
        this.setConversationProgressBar();

        this.setAudioPlayer();
    }

    private void setConversationLabel() {
        this.conversationLabel = new JLabel();

        int x = (this.getWidth() - CONVERSATION_LABEL_WIDTH) / 2;
        this.conversationLabel.setBounds(x, LayoutConstants.MARGIN * 5, CONVERSATION_LABEL_WIDTH, CONVERSATION_LABEL_HEIGHT);

        String imageVal = GlobalConstants.TESTS_DIR + MApplication.settings.get("testIndex") + GlobalConstants.LISTENING_DIR + this.listeningSection.getImages().get(0).getIndex();
        ImageIcon icon = new ImageIcon(this.getClass().getResource(imageVal));
        this.conversationLabel.setIcon(icon);

        this.add(this.conversationLabel);
    }

    private void setConversationProgressBar() {
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

    private void setAudioPlayer() {
        Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
        Format input2 = new AudioFormat(AudioFormat.MPEG);
        Format output = new AudioFormat(AudioFormat.LINEAR);
        PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]{input1, input2}, new Format[]{output}, PlugInManager.CODEC);
        try {
            String audioVal = GlobalConstants.TESTS_DIR + MApplication.settings.get("testIndex") + GlobalConstants.LISTENING_DIR + this.listeningSection.getAudios().get(0).getIndex();
            this.audioPlayer = Manager.createRealizedPlayer(new MediaLocator(this.getClass().getResource(audioVal)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;

        Color bg = new Color(255, 255, 255); // #ffffff

        g2d.setPaint(bg);
        g2d.fillRect(0, 0, width, height);
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
}
