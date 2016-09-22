package com.mocktpo.view.container;

import com.mocktpo.view.window.AppWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class MyContentPane extends JPanel {

    /* Logger */

    protected static final Logger logger = LogManager.getLogger();

    /* Application Window */

    protected AppWindow appWindow;

    public MyContentPane(AppWindow owner) {
        this.setAppWindow(owner);
        this.setBounds(owner.getBounds());
    }

    public AppWindow getAppWindow() {
        return appWindow;
    }

    public void setAppWindow(AppWindow appWindow) {
        this.appWindow = appWindow;
    }
}
