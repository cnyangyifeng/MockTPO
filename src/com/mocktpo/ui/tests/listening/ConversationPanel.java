package com.mocktpo.ui.tests.listening;

import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;

import javax.media.*;
import javax.media.format.AudioFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ConversationPanel extends BodyPanel implements ActionListener {

    public ConversationPanel(Rectangle bounds) {
        super(bounds);
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setConversationLabel();
        this.setConversationProgressBar();

        this.add(this.conversationLabel);
        this.add(this.conversationProgressBar);

        this.setAudioPlayer();
    }

    private void setConversationLabel() {
        this.conversationLabel = new JLabel();

        int x = (this.getWidth() - LayoutConstants.CONVERSATION_LABEL_WIDTH) / 2;
        this.conversationLabel.setBounds(x, LayoutConstants.MARGIN * 5, LayoutConstants.CONVERSATION_LABEL_WIDTH, LayoutConstants.CONVERSATION_LABEL_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "conversation.png"));
        this.conversationLabel.setIcon(icon);
    }

    private void setConversationProgressBar() {
        this.conversationProgressBar = new JProgressBar();

        int x = (this.getWidth() - LayoutConstants.CONVERSATION_PROGRESS_BAR_WIDTH) / 2;
        int y = this.conversationLabel.getY() + LayoutConstants.CONVERSATION_LABEL_HEIGHT + LayoutConstants.MARGIN * 10;
        this.conversationProgressBar.setBounds(x, y, LayoutConstants.CONVERSATION_PROGRESS_BAR_WIDTH, LayoutConstants.CONVERSATION_PROGRESS_BAR_HEIGHT);

        this.conversationProgressBar.setMinimum(0);
        this.conversationProgressBar.setMaximum(100);
        this.conversationProgressBar.setValue(0);
        this.conversationProgressBar.setStringPainted(false);
        this.conversationProgressBar.setIndeterminate(false);
    }

    private void setAudioPlayer() {
        URL url = this.getClass().getResource(GlobalConstants.AUDIO_ROOT + "conversation.mp3");
        Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
        Format input2 = new AudioFormat(AudioFormat.MPEG);
        Format output = new AudioFormat(AudioFormat.LINEAR);
        PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]{input1, input2}, new Format[]{output}, PlugInManager.CODEC);
        try {
            this.audioPlayer = Manager.createRealizedPlayer(new MediaLocator(url));
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
                    if (now < duration) {
                        int progress = (int) ((now * 100) / duration);
                        if (progress < 99) {
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
     * Properties
     **************************************************/

    private JLabel conversationLabel;
    private JProgressBar conversationProgressBar;
    private Player audioPlayer;
    private Timer timer;
}
