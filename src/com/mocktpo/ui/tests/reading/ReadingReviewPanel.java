package com.mocktpo.ui.tests.reading;

import com.mocktpo.model.MChoiceQuestion;
import com.mocktpo.model.MReadingPassage;
import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.ui.widgets.MReadingReviewTable;
import com.mocktpo.util.LayoutConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class ReadingReviewPanel extends BodyPanel {

    /* Constants */

    public static final int DESCRIPTION_PANE_HEIGHT = 160;

    /* Components */

    protected JEditorPane descriptionPane;
    protected MReadingReviewTable bodyTable;

    /* Variables */

    private MReadingPassage passage;

    /**************************************************
     * Constructors
     **************************************************/

    public ReadingReviewPanel(Rectangle bounds, MReadingPassage passage) {
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
        this.setDescriptionPane();
        this.setBodyScrollPane();
    }

    @Override
    protected void initButtonStatus() {
        this.setSectionExitButtonAvailable(true);
        this.setGoToQuestionButtonAvailable(true);
        this.setReturnButtonAvailable(true);
        this.setTimerLabelAvailable(true);
        this.setHideOrShowTimerButtonAvailable(true);
    }

    protected void setDescriptionPane() {
        this.descriptionPane = new JEditorPane();

        this.descriptionPane.setBounds(0, 0, this.getWidth(), DESCRIPTION_PANE_HEIGHT);

        this.descriptionPane.setEditable(false);
        this.descriptionPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".rr { color: #333333; font-family: Roboto; font-size: 11px; margin-left: 20px; margin-top: 16px; margin-right: 20px; }");
        this.descriptionPane.setEditorKit(kit);
        String text = "<div class='rr'>Below is the list of questions in this section. The question you were looking at last is highlighted when you enter Review. The Status column shows if a question has been answered, not answered, or not seen. When a question is worth more than one point, the Status column will indicate that the question has been answered, even if it is only partially answered.</div>";
        text += "<div class='rr'>To review a specific question from the list, click on the question to highlight it, then click on <b>Go to Question</b> at the top of the screen. When there are more questions than will fit on the screen, you can use the scroll bar to view the others.</div>";
        text += "<div class='rr'>To leave Review and return to where you were in the test, click on <b>Return</b>.</div>";
        this.descriptionPane.setText(text);

        this.add(this.descriptionPane);
    }

    protected void setBodyScrollPane() {
        String[] columnNames = {"Number", "Description", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(columnNames);

        List<MChoiceQuestion> questions = this.passage.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Vector<String> v = new Vector<String>();
            v.add(0, new Integer(questions.get(i).getNumber()).toString()); // "Number"
            v.add(1, questions.get(i).getSubject()); // "Description"
            v.add(2, "Not Seen"); // "Status"
            tableModel.addRow(v);
        }

        this.bodyTable = new MReadingReviewTable(tableModel);

        JScrollPane bodyScrollPane = new JScrollPane();
        int x = LayoutConstants.MARGIN * 4;
        int y = this.descriptionPane.getY() + this.descriptionPane.getHeight() + LayoutConstants.MARGIN * 5;
        int width = this.getWidth() - LayoutConstants.MARGIN * 8;
        int height = this.getHeight() - y - LayoutConstants.MARGIN * 10;
        bodyScrollPane.setBounds(x, y, width, height);

        bodyScrollPane.setViewportView(this.bodyTable);

        this.add(bodyScrollPane);
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
