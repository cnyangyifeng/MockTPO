package com.mocktpo.listener;

import com.mocktpo.window.AppWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToTestsHomeViewListener extends MyListener implements ActionListener {

    /* Application Window */

    protected AppWindow appWindow;

    public ToTestsHomeViewListener(AppWindow appWindow) {
        this.setAppWindow(appWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AppWindow win = ToTestsHomeViewListener.this.getAppWindow();
                win.setContentPane(win.getTestsHomeView());
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
