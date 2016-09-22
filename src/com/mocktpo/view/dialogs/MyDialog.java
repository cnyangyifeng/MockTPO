package com.mocktpo.view.dialogs;

import com.mocktpo.view.window.AppWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class MyDialog extends JDialog {

    /* Logger */

    protected static final Logger logger = LogManager.getLogger();

    /* Application Window */

    protected AppWindow appWindow;

    public MyDialog(AppWindow owner, String title, boolean modal) {
        super(owner, title, modal);
        this.setAppWindow(owner);
    }

    public AppWindow getAppWindow() {
        return appWindow;
    }

    public void setAppWindow(AppWindow appWindow) {
        this.appWindow = appWindow;
    }
}
