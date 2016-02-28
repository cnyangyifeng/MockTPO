package com.mocktpo.ui.windows;

import com.mocktpo.ui.dialogs.ApplicationExitDialog;
import com.mocktpo.ui.dialogs.ContactUsDialog;
import com.mocktpo.ui.widgets.*;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    /* Constants */

    public static final int EXIT_APPLICATION_BUTTON_WIDTH = 84;
    public static final int EXIT_APPLICATION_BUTTON_HEIGHT = 34;
    public static final int SLOGAN_PANE_WIDTH = 1000;
    public static final int SLOGAN_PANE_HEIGHT = 80;
    public static final int MODULE_BUTTON_WIDTH = 360;
    public static final int MODULE_BUTTON_HEIGHT = 240;

    /* Logger */

    protected static final Logger logger = LogManager.getLogger();

    /* Frames */

    protected TestsHomeFrame testsHomeFrame;
    protected PracticesHomeFrame practicesHomeFrame;

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

    public MainFrame(GraphicsConfiguration gc) {
        super(gc);
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
        this.setBodyPanel();
        this.setHeaderPanel();
        this.setFooterPanel();
    }

    /**************************************************
     * Global Settings
     **************************************************/

    protected void globalSettings() {
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
        this.setLogoLabel();
        this.setTitlePane();
        this.setExitApplicationButton();
        /* Add to the parent component */
        this.getContentPane().add(this.headerPanel);
    }

    protected void setLogoLabel() {
        /* Initialize component */
        JLabel logoLabel = new JLabel();
        /* Set bounds */
        logoLabel.setBounds(0, LayoutConstants.MARGIN, LayoutConstants.LOGO_LABEL_WIDTH, LayoutConstants.LOGO_LABEL_HEIGHT);
        /* Set icon */
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "logo.png"));
        logoLabel.setIcon(icon);
        /* Add to the parent component */
        this.headerPanel.add(logoLabel);
    }

    protected void setTitlePane() {
        /* Initialize component */
        int x = LayoutConstants.LOGO_LABEL_WIDTH + LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        String css = ".title { font-family: Impact; font-size: 24px; color: #ffffff; }";
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
        this.getContentPane().add(this.bodyPanel);
    }

    protected void setSloganPane() {
        /* Initialize component */
        int x = (this.bodyPanel.getWidth() - SLOGAN_PANE_WIDTH) / 2;
        int y = LayoutConstants.MARGIN * 12;
        String css = ".slogan { color: #333333; font-family: Roboto; font-size: 24px; text-align: center; } .slogan-desc { color: #999999; font-family: Roboto; font-size: 16px; margin-top: 10px; text-align: center; } a.author { color: #333333; }";
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
                            ContactUsDialog contactUs = new ContactUsDialog(MainFrame.this, "", true);
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
        this.testsHomeButton = new ModuleButton(x, y, MODULE_BUTTON_WIDTH, MODULE_BUTTON_HEIGHT, "MODEL TESTS");
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
        this.practicesHomeButton = new ModuleButton(x, y, MODULE_BUTTON_WIDTH, MODULE_BUTTON_HEIGHT, "PRACTICES");
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
        this.getContentPane().add(this.footerPanel);
    }

    protected void setCopyrightPane() {
        /* Initialize component */
        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        String css = ".copyright { color: #ffffff; font-family: Roboto; font-size: 8px; font-weight: bold; text-align: center; }";
        String html = "<div class='copyright'>Copyright 2006, 2010, 2011 by Educational Testing Service. All rights reserved. EDUCATIONAL TESTING SERVICE, ETS, the ETS logo, TOEFL and TOEFL iBT are registered trademarks of Educational Testing Service (ETS) in the United States and other countries.</div>";
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
                    ApplicationExitDialog exit = new ApplicationExitDialog(MainFrame.this, "", true);
                    exit.setVisible(true);
                }
            });
        } else if (("goToTestsHome").equals(e.getActionCommand())) {
            logger.info("'Model Tests' button pressed.");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice device = ge.getDefaultScreenDevice();
                    testsHomeFrame = new TestsHomeFrame(device.getDefaultConfiguration(), MainFrame.this, GlobalConstants.APPLICATION_NAME);
                    device.setFullScreenWindow(testsHomeFrame);
                    testsHomeFrame.setVisible(true);
                    setVisible(false);
                }
            });
        } else if (("goToPracticesHome").equals(e.getActionCommand())) {
            logger.info("'Practices' button pressed.");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice device = ge.getDefaultScreenDevice();
                    practicesHomeFrame = new PracticesHomeFrame(device.getDefaultConfiguration(), MainFrame.this, GlobalConstants.APPLICATION_NAME);
                    device.setFullScreenWindow(practicesHomeFrame);
                    practicesHomeFrame.setVisible(true);
                    setVisible(false);
                }
            });
        }
    }

    /**************************************************
     * Actions
     **************************************************/
}
