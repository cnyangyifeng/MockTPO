package com.mocktpo.view.window;

import com.mocktpo.config.UserConfig;
import com.mocktpo.util.ConfigUtils;
import com.mocktpo.util.FontsUtils;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.view.container.IndexContentPane;
import com.mocktpo.view.container.PracticesHomeContentPane;
import com.mocktpo.view.container.TestContentPane;
import com.mocktpo.view.container.TestsHomeContentPane;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {

    /* Configuration Data */

    protected UserConfig userConfig;

    /* Content Panes */

    protected IndexContentPane indexContentPane;
    protected TestsHomeContentPane testsHomeContentPane;
    protected TestContentPane testContentPane;
    protected PracticesHomeContentPane practicesHomeContentPane;

    /**************************************************
     * Constructors
     **************************************************/

    public AppWindow(GraphicsConfiguration gc) {
        super(gc);
        this.configData();
        this.globalSettings();
        this.initComponents();
    }

    /**************************************************
     * Configure Data
     **************************************************/

    protected void configData() {
        UserConfig uc = ConfigUtils.load("user_config.json", UserConfig.class);
        this.setUserConfig(uc);
    }

    /**************************************************
     * Global Settings
     **************************************************/

    protected void globalSettings() {
        /* Load fonts */
        FontsUtils.loadFonts();
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

    /**************************************************
     * Components Initialization
     **************************************************/

    private void initComponents() {
        /* Set layout */
        this.setLayout(null);
        /* Set components */
        this.setContentPane(this.getIndexContentPane());
    }

    /**************************************************
     * Getters and Setters
     **************************************************/

    public UserConfig getUserConfig() {
        return userConfig;
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public IndexContentPane getIndexContentPane() {
        if (null == this.indexContentPane) {
            this.indexContentPane = new IndexContentPane(this);
        }
        return indexContentPane;
    }

    public TestsHomeContentPane getTestsHomeContentPane() {
        if (null == this.testsHomeContentPane) {
            this.testsHomeContentPane = new TestsHomeContentPane(this);
        }
        return testsHomeContentPane;
    }

    public TestContentPane getTestContentPane() {
        if (null == this.testContentPane) {
            this.testContentPane = new TestContentPane(this);
        }
        return testContentPane;
    }

    public PracticesHomeContentPane getPracticesHomeContentPane() {
        if (null == this.practicesHomeContentPane) {
            this.practicesHomeContentPane = new PracticesHomeContentPane(this);
        }
        return practicesHomeContentPane;
    }
}
