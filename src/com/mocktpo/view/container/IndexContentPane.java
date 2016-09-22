package com.mocktpo.view.container;

import com.mocktpo.util.FontsConstants;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import com.mocktpo.view.dialogs.ApplicationExitDialog;
import com.mocktpo.view.dialogs.ContactUsDialog;
import com.mocktpo.view.widgets.*;
import com.mocktpo.view.window.AppWindow;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexContentPane extends MyContentPane implements ActionListener {

    /* Constants */

    public static final int EXIT_APPLICATION_BUTTON_WIDTH = 84;
    public static final int EXIT_APPLICATION_BUTTON_HEIGHT = 34;
    public static final int SLOGAN_PANE_WIDTH = 1000;
    public static final int SLOGAN_PANE_HEIGHT = 80;
    public static final int MODULE_BUTTON_WIDTH = 360;
    public static final int MODULE_BUTTON_HEIGHT = 240;

    /* Components */

    protected HeaderPanel headerPanel;
    protected BodyPanel bodyPanel;
    protected StyledLabelPane sloganPane;
    protected ModuleButton testsHomeButton;
    protected ModuleButton practicesHomeButton;
    protected FooterPanel footerPanel;

    /**************************************************
     * Constructors
     **************************************************/

    public IndexContentPane(AppWindow owner) {
        super(owner);
        this.configData();
        this.initComponents();
    }

    /**************************************************
     * Configure Data
     **************************************************/

    protected void configData() {
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
        this.setBodyPanel();
        this.setHeaderPanel();
        this.setFooterPanel();
    }

    /**************************************************
     * Global Settings
     **************************************************/

    protected void globalSettings() {
    }

    /**************************************************
     * Header Panel Settings
     **************************************************/

    protected void setHeaderPanel() {
        /* Initialize component */
        this.headerPanel = new HeaderPanel();
        /* Set bounds */
        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);
        /* Set layout */
        this.headerPanel.setLayout(null);
        /* Set children components */
        this.setTitlePane();
        this.setExitApplicationButton();
        /* Add to the parent component */
        this.add(this.headerPanel);
    }

    protected void setTitlePane() {
        /* Initialize component */
        int x = LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        String css = ".title { font-family: " + FontsConstants.LOGO_FONT + "; font-size: 20px; font-weight: bold; color: #ffffff; }";
        String html = "<div class='title'>" + GlobalConstants.APPLICATION_NAME + "</div>";
        StyledLabelPane titlePane = new StyledLabelPane(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.headerPanel.add(titlePane);
    }

    protected void setExitApplicationButton() {
        /* Initialize component */
        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - EXIT_APPLICATION_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit_application.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit_application_hi.png"));
        ImageButton exitApplicationButton = new ImageButton(x, y, EXIT_APPLICATION_BUTTON_WIDTH, EXIT_APPLICATION_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        exitApplicationButton.setActionCommand("doExitApplication");
        exitApplicationButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(exitApplicationButton);
    }

    /**************************************************
     * Body Panel Settings
     **************************************************/

    protected void setBodyPanel() {
        /* Initialize component */
        int y = LayoutConstants.HEADER_PANEL_HEIGHT;
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        Rectangle bounds = new Rectangle(0, y, this.getWidth(), height);
        this.bodyPanel = new BodyPanel(bounds);
        /* Set children components */
        this.setSloganPane();
        this.setTestHomeButton();
        this.setPracticeHomeButton();
        /* Add to the parent component */
        this.add(this.bodyPanel);
    }

    protected void setSloganPane() {
        /* Initialize component */
        int x = (this.bodyPanel.getWidth() - SLOGAN_PANE_WIDTH) / 2;
        int y = LayoutConstants.MARGIN * 12;
        String css = ".slogan { color: #333333; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 24px; text-align: center; } .slogan-desc { color: #999999; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 16px; margin-top: 10px; text-align: center; } a.author { color: #333333; }";
        String html = "<div class='slogan'>" + GlobalConstants.APPLICATION_NAME + " is a TOEFL&reg; iBT Practice Offline Application</div>" +
                "<div class='slogan-desc'>Please contact <a href='' class='author'>us</a> to report bugs and check updates.</div>";
        this.sloganPane = new StyledLabelPane(x, y, SLOGAN_PANE_WIDTH, SLOGAN_PANE_HEIGHT, css, html);
        /* Add hyperlink listener */
        this.sloganPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    logger.info("'Contact Us' link clicked.");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            ContactUsDialog contactUs = new ContactUsDialog(IndexContentPane.this.getAppWindow(), "", true);
                            contactUs.setVisible(true);
                        }
                    });
                }
            }
        });
        /* Add to the parent component */
        this.bodyPanel.add(this.sloganPane);
    }

    protected void setTestHomeButton() {
        /* Initialize component */
        int x = (this.bodyPanel.getWidth() - MODULE_BUTTON_WIDTH * 2 - LayoutConstants.MARGIN * 10) / 2;
        int y = this.sloganPane.getY() + this.sloganPane.getHeight() + LayoutConstants.MARGIN * 20;
        this.testsHomeButton = new ModuleButton(x, y, MODULE_BUTTON_WIDTH, MODULE_BUTTON_HEIGHT, GlobalConstants.TESTS_HOME_TITLE);
        /* Set actions */
        this.testsHomeButton.setActionCommand("goToTestsHome");
        this.testsHomeButton.addActionListener(this);
        /* Add to the parent component */
        this.bodyPanel.add(this.testsHomeButton);
    }

    protected void setPracticeHomeButton() {
        /* Initialize component */
        int x = (this.bodyPanel.getWidth() + LayoutConstants.MARGIN * 10) / 2;
        int y = this.testsHomeButton.getY();
        this.practicesHomeButton = new ModuleButton(x, y, MODULE_BUTTON_WIDTH, MODULE_BUTTON_HEIGHT, GlobalConstants.PRACTICES_HOME_TITLE);
        /* Set actions */
        this.practicesHomeButton.setActionCommand("goToPracticesHome");
        this.practicesHomeButton.addActionListener(this);
        /* Add to the parent component */
        this.bodyPanel.add(this.practicesHomeButton);
    }

    /**************************************************
     * Footer Panel Settings
     **************************************************/

    protected void setFooterPanel() {
        /* Initialize component */
        this.footerPanel = new FooterPanel();
        /* Set bounds */
        int y = this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT;
        this.footerPanel.setBounds(0, y, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);
        /* Set layout */
        this.footerPanel.setLayout(null);
        /* Set children components */
        this.setCopyrightPane();
        /* Add to the parent component */
        this.add(this.footerPanel);
    }

    protected void setCopyrightPane() {
        /* Initialize component */
        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        String css = ".copyright { color: #ffffff; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 8px; font-weight: bold; text-align: center; }";
        String html = "<div class='copyright'>" + GlobalConstants.COPYRIGHT_INFO + "</div>";
        StyledLabelPane copyrightPane = new StyledLabelPane(x, y, LayoutConstants.COPYRIGHT_PANE_WIDTH, LayoutConstants.COPYRIGHT_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.footerPanel.add(copyrightPane);
    }

    /**************************************************
     * Listener Implementations
     **************************************************/

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("doExitApplication".equals(e.getActionCommand())) {
            logger.info("'Exit Application' button pressed.");
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ApplicationExitDialog exit = new ApplicationExitDialog(IndexContentPane.this.getAppWindow(), "", true);
                    exit.setVisible(true);
                }
            });
        } else if (("goToTestsHome").equals(e.getActionCommand())) {
            logger.info("'Model Tests' button pressed.");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    AppWindow win = IndexContentPane.this.getAppWindow();
                    win.setContentPane(win.getTestsHomeContentPane());
                }
            });
        } else if (("goToPracticesHome").equals(e.getActionCommand())) {
            logger.info("'Practices' button pressed.");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    AppWindow win = IndexContentPane.this.getAppWindow();
                    win.setContentPane(win.getPracticesHomeContentPane());
                }
            });
        }
    }

    /**************************************************
     * Actions
     **************************************************/

    // TODO Actions

    /**************************************************
     * Getters and Setters
     **************************************************/

    // TODO Getters and setters
}
