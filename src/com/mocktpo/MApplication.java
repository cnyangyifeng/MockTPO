package com.mocktpo;

import com.mocktpo.ui.windows.MainFrame;
import com.mocktpo.util.FontsUtils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MApplication {

    /* Constants */

    public static final Map<String, Object> settings = new HashMap<String, Object>();

    /**************************************************
     * The Application Main Method
     **************************************************/

    public static void main(String[] args) {
        FontsUtils.loadFonts();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = ge.getDefaultScreenDevice();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame = new MainFrame(device.getDefaultConfiguration());
                device.setFullScreenWindow(mainFrame);
                mainFrame.setVisible(true);
            }
        });
    }
}
