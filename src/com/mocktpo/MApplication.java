package com.mocktpo;

import com.mocktpo.ui.windows.MainFrame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MApplication {

    public static final Map<String, Object> settings = new HashMap<String, Object>();

    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = ge.getDefaultScreenDevice();
        MainFrame mainFrame = new MainFrame(device.getDefaultConfiguration());
        device.setFullScreenWindow(mainFrame);
        mainFrame.setVisible(true);
    }
}
