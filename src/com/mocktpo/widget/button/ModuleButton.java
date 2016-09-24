package com.mocktpo.widget.button;

import com.mocktpo.util.FontsConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ModuleButton extends JButton implements MouseListener {

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
        this.setFont(new Font(FontsConstants.SYSTEM_FONT, Font.PLAIN, 30));
        /* Set foreground */
        this.setForeground(new Color(51, 51, 51)); // #333333
        /* Set background */
        this.setOpaque(true);
        this.setBackground(new Color(245, 245, 245));
        /* Set border */
        this.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102), 2, false));
        /* Set cursor */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        /* Set actions */
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.active();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.deactive();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.active();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.deactive();
    }

    private void active() {
        /* Set foreground */
        this.setForeground(new Color(60, 77, 130));
        /* Set border */
        this.setBorder(BorderFactory.createLineBorder(new Color(60, 77, 130), 2, false));
    }

    private void deactive() {
        /* Set foreground */
        this.setForeground(new Color(51, 51, 51)); // #333333
        /* Set border */
        this.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102), 2, false));
    }
}
