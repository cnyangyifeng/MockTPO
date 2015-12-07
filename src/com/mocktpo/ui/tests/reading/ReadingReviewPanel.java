package com.mocktpo.ui.tests.reading;

import com.mocktpo.model.MReadingPassage;
import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.ui.widgets.MTable;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;

public class ReadingReviewPanel extends BodyPanel {

    protected JEditorPane descriptionPane;
    protected MTable bodyTable;

    private MReadingPassage passage;

    public ReadingReviewPanel(Rectangle bounds, MReadingPassage passage) {
        super(bounds);
        this.passage = passage;
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setDescriptionPane();
    }

    @Override
    protected void initButtonStatus() {
        this.setSectionExitButtonAvailable(true);
        this.setContinueOvalButtonAvailable(true);
        this.setTimerLabelAvailable(true);
        this.setHideOrShowTimerButtonAvailable(true);
    }

    protected void setDescriptionPane() {
        this.descriptionPane = new JEditorPane();

        this.descriptionPane.setBounds(0, 0, this.getWidth(), this.getHeight());

        this.descriptionPane.setEditable(false);
        this.descriptionPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".rr { color: #333333; font-family: Arial; font-size: 11px; margin-left: 20px; margin-top: 20px; margin-right: 20px; }");
        this.descriptionPane.setEditorKit(kit);
        String text = "<div class='rr'>Below is the list of questions in this section. The question you were looking at last is highlighted when you enter Review. The Status column shows if a question has been answered, not answered, or not seen. When a question is worth more than one point, the Status column will indicate that the question has been answered, even if it is only partially answered.</div>";
        text += "<div class='rr'>To review a specific question from the list, click on the question to highlight it, then click on <b>Go to Question</b> at the top of the screen. When there are more questions than will fit on the screen, you can use the scroll bar to view the others.</div>";
        text += "<div class='rr'>To leave Review and return to where you were in the test, click on <b>Return</b>.</div>";
        this.descriptionPane.setText(text);

        this.add(this.descriptionPane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        Color bg = new Color(242, 232, 200); // #f2e8c8
        g2d.setPaint(bg);
        g2d.fillRect(0, 0, width, height);
    }

    /**************************************************
     * Actions
     **************************************************/

}
