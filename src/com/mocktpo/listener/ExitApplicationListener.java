package com.mocktpo.listener;

import com.mocktpo.widget.dialog.ApplicationExitDialog;
import com.mocktpo.window.AppWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitApplicationListener extends MyListener implements ActionListener {

    /* Application Window */

    protected AppWindow appWindow;

    public ExitApplicationListener(AppWindow appWindow) {
        this.setAppWindow(appWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ApplicationExitDialog exit = new ApplicationExitDialog(ExitApplicationListener.this.getAppWindow(), "", true);
                exit.setVisible(true);
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
