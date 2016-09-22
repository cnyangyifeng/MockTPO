package com.mocktpo.view.widgets;

import com.mocktpo.util.FontsConstants;

import javax.swing.*;
import java.awt.*;

public class ModuleButton extends JButton {

    /**************************************************
     * Constructors
     **************************************************/

    public ModuleButton(int x, int y, int width, int height, String text) {
        super();
        /* Set bounds */
        this.setBounds(x, y, width, height);
        /* Set text*/
        this.setText(text);
        /* Set font */
        this.setFont(new Font(FontsConstants.SYSTEM_FONT, Font.PLAIN, 24));
        /* Set foreground */
        this.setForeground(new Color(51, 51, 51)); // #333333
        /* Set background */
        this.setBackground(new Color(255, 255, 255));
        /* Set border */
        this.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102), 2, false));
        /* Set cursor */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
