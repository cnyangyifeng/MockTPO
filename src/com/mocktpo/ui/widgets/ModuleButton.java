package com.mocktpo.ui.widgets;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModuleButton extends JButton {

    /* Border colors */

    private Color borderColor = new Color(102, 102, 102); // #666666
    private Color borderFocusColor = new Color(60, 77, 130); // #3c4d82

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
        this.setFont(new Font("Roboto", Font.PLAIN, 24));
        /* Set foreground */
        this.setForeground(new Color(51, 51, 51)); // #333333
        /* Set background */
        this.setBackground(new Color(255, 255, 255));
        /* Set border */
        this.setBorder(BorderFactory.createLineBorder(borderColor, 2, false));
        /* Set cursor */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        /* Add focus listener */
        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    setBorder(BorderFactory.createLineBorder(borderFocusColor, 2, false));
                } else {
                    setBorder(BorderFactory.createLineBorder(borderColor, 2, false));
                }
            }
        });
    }
}
