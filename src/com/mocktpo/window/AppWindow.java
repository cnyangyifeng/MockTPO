package com.mocktpo.window;

import com.mocktpo.MyApplication;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.view.IndexView;
import com.mocktpo.view.PracticesHomeView;
import com.mocktpo.view.TestView;
import com.mocktpo.view.TestsHomeView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {

    /* Logger */

    protected static final Logger logger = LogManager.getLogger();

    /* Application */

    private MyApplication application;

    /* Views */

    private IndexView indexView;
    private TestsHomeView testsHomeView;
    private PracticesHomeView practicesHomeView;

    private TestView testView;

    /**************************************************
     * Constructors
     **************************************************/

    public AppWindow() {
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
        this.setContentPane(this.getIndexView());
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

    /**************************************************
     * Getters and Setters
     **************************************************/

    public MyApplication getApplication() {
        return application;
    }

    public void setApplication(MyApplication application) {
        this.application = application;
    }

    public IndexView getIndexView() {
        if (null == this.indexView) {
            this.indexView = new IndexView(this);
        }
        return indexView;
    }

    public TestsHomeView getTestsHomeView() {
        if (null == this.testsHomeView) {
            this.testsHomeView = new TestsHomeView(this);
        }
        return testsHomeView;
    }

    public TestView getTestView() {
        if (null == this.testView) {
            this.testView = new TestView(this);
        }
        return testView;
    }

    public PracticesHomeView getPracticesHomeView() {
        if (null == this.practicesHomeView) {
            this.practicesHomeView = new PracticesHomeView(this);
        }
        return practicesHomeView;
    }
}
