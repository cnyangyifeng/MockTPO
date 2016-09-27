package com.mocktpo.window;

import com.mocktpo.util.GlobalConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class RegisterWindow extends JFrame {


    /* Logger */

    protected static final Logger logger = LogManager.getLogger();


    /**************************************************
     * Constructors
     **************************************************/

    public RegisterWindow() {
        this.initComponents();
    }

    /**************************************************
     * Components Initialization
     **************************************************/

    private void initComponents() {
        /* Global settings */
        this.globalSettings();
        /* Set layout */
        this.setLayout(null);
        /* Set components */
    }


    /**************************************************
     * Global Settings
     **************************************************/

    private void globalSettings() {
        /* Set bounds */
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        Rectangle bounds = new Rectangle(screenSize);
        this.setBounds(bounds);
        /* Set maximized, unresizable and undecorated */
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setUndecorated(true);
        /* Set title */
        this.setTitle(GlobalConstants.APPLICATION_NAME);
    }
}
