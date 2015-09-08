package com.mocktpo.ui;

import com.mocktpo.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

public class ConversationPanel extends JPanel implements PropertyChangeListener {

    public ConversationPanel(JComponent owner) {
        super();
        this.owner = owner;
        this.initComponents();
    }

    private void initComponents() {
        this.setBounds(0, 0, this.owner.getWidth(), this.owner.getHeight());

        this.setLayout(null);

        this.setConversationLabel();
        this.setConversationProgressBar();

        this.add(this.conversationLabel);
        this.add(this.conversationProgressBar);

        this.audioStart();
    }

    private void setConversationLabel() {
        this.conversationLabel = new JLabel();

        int x = (this.getWidth() - Constants.CONVERSATION_LABEL_WIDTH) / 2;
        this.conversationLabel.setBounds(x, Constants.MARGIN * 4, Constants.CONVERSATION_LABEL_WIDTH, Constants.CONVERSATION_LABEL_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("conversation.png"));
        this.conversationLabel.setIcon(icon);
    }

    private void setConversationProgressBar() {
        this.conversationProgressBar = new JProgressBar();

        int x = (this.getWidth() - Constants.CONVERSATION_PROGRESS_BAR_WIDTH) / 2;
        int y = this.conversationLabel.getY() + Constants.CONVERSATION_LABEL_HEIGHT + Constants.MARGIN * 4;
        this.conversationProgressBar.setBounds(x, y, Constants.CONVERSATION_PROGRESS_BAR_WIDTH, Constants.CONVERSATION_PROGRESS_BAR_HEIGHT);

        this.conversationProgressBar.setMinimum(0);
        this.conversationProgressBar.setMaximum(100);
        this.conversationProgressBar.setValue(100);
        this.conversationProgressBar.setStringPainted(false);
        this.conversationProgressBar.setIndeterminate(false);
    }

    private void audioStart() {
        URL url = this.getClass().getResource("conversation.mp3");
        AudioWorker worker = new AudioWorker(url);
        worker.addPropertyChangeListener(this);
        worker.execute();
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

    /**************************************************
     * Listeners
     **************************************************/

    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress".equals(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            this.conversationProgressBar.setValue(progress);
        }
    }

    /**************************************************
     * Properties
     **************************************************/

    private JComponent owner;
    private JLabel conversationLabel;
    private JProgressBar conversationProgressBar;
}
