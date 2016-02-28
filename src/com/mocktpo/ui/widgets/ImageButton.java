package com.mocktpo.ui.widgets;

import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {

    /**************************************************
     * Constructors
     **************************************************/

    public ImageButton(int x, int y, int width, int height, ImageIcon icon, ImageIcon rolloverIcon) {
        super();
        /* Set bounds */
        this.setBounds(x, y, width, height);
        /* Set icon */
        this.setIcon(icon);
        /* Set rollover icon */
        this.setRolloverIcon(rolloverIcon);
        /* Set cursor */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        /* Set other features */
        this.setText(null);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorder(null);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
    }
}
