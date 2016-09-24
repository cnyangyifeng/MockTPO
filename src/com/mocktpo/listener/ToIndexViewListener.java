package com.mocktpo.listener;

import com.mocktpo.window.AppWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToIndexViewListener extends MyListener implements ActionListener {

    /* Application Window */

    protected AppWindow appWindow;

    public ToIndexViewListener(AppWindow appWindow) {
        this.setAppWindow(appWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AppWindow win = ToIndexViewListener.this.getAppWindow();
                win.setContentPane(win.getIndexView());
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
