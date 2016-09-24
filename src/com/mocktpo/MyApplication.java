package com.mocktpo;

import com.mocktpo.util.FontsUtils;
import com.mocktpo.window.AppWindow;
import com.mocktpo.window.SplashWindow;

import java.awt.*;

public class MyApplication {

    public void init() {
        SplashWindow splash = new SplashWindow();
        splash.setVisible(true);

        splash.update(20);
        FontsUtils.loadFonts();

        splash.update(60);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = ge.getDefaultScreenDevice();
        AppWindow win = new AppWindow(device.getDefaultConfiguration());

        splash.update(100);
        this.sleep(300);

        /* Close splash window */
        splash.close();
        this.sleep(100);
        /* Set application window visible */
        win.setOpacity(0);
        win.setVisible(true);
        device.setFullScreenWindow(win);
    }

    private void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**************************************************
     * The Application Main Method
     **************************************************/

    public static void main(String[] args) {
        new MyApplication().init();
    }
}
