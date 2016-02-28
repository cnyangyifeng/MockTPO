package com.mocktpo.ui.widgets;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class StyledLabelPane extends JEditorPane {

    /**************************************************
     * Constructors
     **************************************************/

    public StyledLabelPane(int x, int y, int width, int height, String css, String html) {
        super();
        /* Set bounds */
        this.setBounds(x, y, width, height);
        /* Set uneditable and transparent */
        this.setEditable(false);
        this.setOpaque(false);
        /* Set styles */
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(css);
        this.setEditorKit(kit);
        /* Set text */
        this.setText(html);
    }
}
