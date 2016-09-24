package com.mocktpo.view;

import com.mocktpo.window.AppWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class MyView extends JPanel {

    /* Logger */

    protected static final Logger logger = LogManager.getLogger();

    /* Application Window */

    protected AppWindow appWindow;

    public MyView(AppWindow owner) {
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
