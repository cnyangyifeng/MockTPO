package com.mocktpo.window;

import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import com.mocktpo.widget.panel.ImagePanel;

import javax.swing.*;
import java.awt.*;

public class SplashWindow extends JWindow {

    /* Constants */

    public static final int SPLASH_WINDOW_WIDTH = 480;
    public static final int SPLASH_WINDOW_HEIGHT = 300;
    public static final int BACKGROUND_PANEL_WIDTH = 480;
    public static final int BACKGROUND_PANEL_HEIGHT = 270;
    public static final int PROGRESS_BAR_WIDTH = 460;
    public static final int PROGRESS_BAR_HEIGHT = 30;

    /* Components */

    private JPanel backgroundPanel;
    private JProgressBar progressBar;

    /* Workers */
    private BackgroundPanelWorker backgroundPanelWorker;

    public SplashWindow() {
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
        this.setProgressBar();
        this.setBackgroundPanel();
    }

    /**************************************************
     * Global Settings
     **************************************************/

    private void globalSettings() {
        /* Set bounds */
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int x = (screenSize.width - SPLASH_WINDOW_WIDTH) / 2;
        int y = (screenSize.height - SPLASH_WINDOW_HEIGHT) / 2;
        Rectangle bounds = new Rectangle(x, y, SPLASH_WINDOW_WIDTH, SPLASH_WINDOW_HEIGHT);
        this.setBounds(bounds);
        /* Set background */
        this.setBackground(new Color(51, 51, 51));
        this.getContentPane().setBackground(new Color(51, 51, 51));
    }

    /**************************************************
     * Update
     **************************************************/

    public void update(int value) {
        this.progressBar.setValue(value);
    }

    /**************************************************
     * Close
     **************************************************/

    public void close() {
        this.setVisible(false);
        this.dispose();
    }

    /**************************************************
     * Background Panel Settings
     **************************************************/

    public void setBackgroundPanel() {
        backgroundPanelWorker = new BackgroundPanelWorker();
        backgroundPanelWorker.execute();
    }

    /**************************************************
     * Progress Bar Settings
     **************************************************/

    private void setProgressBar() {
        this.progressBar = new JProgressBar();
        this.progressBar.setBounds(LayoutConstants.MARGIN * 2, BACKGROUND_PANEL_HEIGHT, PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);

        this.progressBar.setMinimum(0);
        this.progressBar.setMaximum(100);
        this.progressBar.setValue(0);
        this.progressBar.setStringPainted(false);
        this.progressBar.setIndeterminate(false);

        this.getContentPane().add(this.progressBar);
    }

    /**************************************************
     * Swing Workers
     **************************************************/

    protected class BackgroundPanelWorker extends SwingWorker<ImagePanel, Void> {

        @Override
        protected ImagePanel doInBackground() {
            return new ImagePanel(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "splash.png"));
        }

        protected void done() {
            try {
                backgroundPanel = get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /* Set bounds */
            backgroundPanel.setBounds(0, 0, BACKGROUND_PANEL_WIDTH, BACKGROUND_PANEL_HEIGHT);
            /* Add to the parent component */
            getContentPane().add(backgroundPanel);
            repaint();
        }
    }

    /**************************************************
     * The Application Main Method
     **************************************************/

    public static void main(String[] args) {
        SplashWindow win = new SplashWindow();
        win.setVisible(true);
    }
}
