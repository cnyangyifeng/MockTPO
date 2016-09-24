package com.mocktpo.listener;

import com.mocktpo.window.AppWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToPracticesHomeViewListener extends MyListener implements ActionListener {

    /* Application Window */

    protected AppWindow appWindow;

    public ToPracticesHomeViewListener(AppWindow appWindow) {
        this.setAppWindow(appWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logger.info("'Model Tests' button pressed.");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AppWindow win = ToPracticesHomeViewListener.this.getAppWindow();
                win.setContentPane(win.getPracticesHomeView());
            }
        });
    }

    public AppWindow getAppWindow() {
        return appWindow;
    }

    public void setAppWindow(AppWindow appWindow) {
        this.appWindow = appWindow;
    }
}
