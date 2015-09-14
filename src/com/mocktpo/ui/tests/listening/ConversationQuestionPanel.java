package com.mocktpo.ui.tests.listening;

import com.mocktpo.ui.widgets.BodyPanel;

import javax.swing.*;
import java.awt.*;

public class ConversationQuestionPanel extends BodyPanel {

    public static final int SUBJECT_LABEL_WIDTH = 600;
    public static final int SUBJECT_LABEL_HEIGHT = 200;

    /**************************************************
     * Properties
     **************************************************/

    private JLabel subjectLabel;

    public ConversationQuestionPanel(Rectangle bounds) {
        super(bounds);
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setSubjectLabel();

        this.add(this.subjectLabel);
    }

    private void setSubjectLabel() {
        this.subjectLabel = new JLabel();

        int x = (this.getWidth() - SUBJECT_LABEL_WIDTH) / 2;
        int y = (this.getHeight() - SUBJECT_LABEL_HEIGHT) / 2;
        this.subjectLabel.setBounds(x, y, SUBJECT_LABEL_WIDTH, SUBJECT_LABEL_HEIGHT);

        this.subjectLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        this.subjectLabel.setText("LALALA");
    }

    public void setSubject(String subject) {
        this.subjectLabel.setText(subject);
    }

    public void setOptions(String options) {
        this.subjectLabel.setText(options);
    }
}
