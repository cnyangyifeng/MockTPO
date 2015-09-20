package com.mocktpo.ui.tests.listening;

import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.util.LayoutConstants;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;

public class ListeningHintsPanel extends BodyPanel {

    public static final int DESCRIPTION_PANE_WIDTH = 500;
    public static final int DESCRIPTION_PANE_HEIGHT = 380;

    /**************************************************
     * Properties
     **************************************************/

    private JEditorPane descriptionPane;
    private String hints;

    public ListeningHintsPanel(Rectangle bounds, String hints) {
        super(bounds);
        this.hints = hints;
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setDescriptionPane();

        this.add(this.descriptionPane);
    }

    private void setDescriptionPane() {
        this.descriptionPane = new JEditorPane();

        int x = (this.getWidth() - DESCRIPTION_PANE_WIDTH) / 2;
        int y = LayoutConstants.MARGIN * 5;
        this.descriptionPane.setBounds(x, y, DESCRIPTION_PANE_WIDTH, DESCRIPTION_PANE_HEIGHT);

        this.descriptionPane.setEditable(false);
        this.descriptionPane.setOpaque(true);
        this.descriptionPane.setBackground(new Color(255, 252, 239));

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".hints { color: #333333; font-family: Arial; font-size: 16px; margin-top: 80px; padding: 30px; text-align: center; }");
        this.descriptionPane.setEditorKit(kit);
        String text = "<div class='hints'>" + this.hints + "</div>";
        this.descriptionPane.setText(text);

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
}
