package com.mocktpo;

import com.mocktpo.view.window.AppWindow;

import javax.swing.*;
import java.awt.*;

public class MyApplication implements Runnable {

    @Override
    public void run() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = ge.getDefaultScreenDevice();
        AppWindow win = new AppWindow(device.getDefaultConfiguration());
        device.setFullScreenWindow(win);
        win.setVisible(true);
    }

    /**************************************************
     * The Application Main Method
     **************************************************/

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MyApplication());
    }
}
