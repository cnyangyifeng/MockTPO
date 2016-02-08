package com.mocktpo.ui.windows;

import com.mocktpo.ui.dialogs.ApplicationExitDialog;
import com.mocktpo.ui.dialogs.ContactUsDialog;
import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.ui.widgets.FooterPanel;
import com.mocktpo.ui.widgets.HeaderPanel;
import com.mocktpo.ui.widgets.MButton;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    // Constants

    public static final int EXIT_APPLICATION_BUTTON_WIDTH = 84;
    public static final int EXIT_APPLICATION_BUTTON_HEIGHT = 34;
    public static final int SLOGAN_PANE_WIDTH = 1000;
    public static final int SLOGAN_PANE_HEIGHT = 80;
    public static final int MODULE_BUTTON_WIDTH = 360;
    public static final int MODULE_BUTTON_HEIGHT = 240;

    // Logger

    protected static final Logger logger = LogManager.getLogger();

    // Frames

    protected TestHomeFrame testHomeFrame;
    protected PracticeHomeFrame practiceHomeFrame;

    // Components

    protected HeaderPanel headerPanel;
    protected BodyPanel bodyPanel;
    protected JEditorPane sloganPane;
    protected MButton testHomeButton;
    protected MButton practiceHomeButton;
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
        this.globalSettings();
        this.setLayout(null);

        this.setBodyPanel();
        this.setHeaderPanel();
        this.setFooterPanel();
    }

    /**************************************************
     * Global Settings
     **************************************************/

    protected void globalSettings() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        Rectangle bounds = new Rectangle(screenSize);
        this.setBounds(bounds);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setUndecorated(true);

        this.setTitle(GlobalConstants.APPLICATION_NAME);
    }

    /**************************************************
     * Set Header Panel
     **************************************************/

    protected void setHeaderPanel() {
        this.headerPanel = new HeaderPanel();

        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);

        this.headerPanel.setLayout(null);

        this.setLogoLabel();
        this.setTitlePane();
        this.setExitApplicationButton();

        this.getContentPane().add(this.headerPanel);
    }

    protected void setLogoLabel() {
        JLabel logoLabel = new JLabel();

        logoLabel.setBounds(0, LayoutConstants.MARGIN, LayoutConstants.LOGO_LABEL_WIDTH, LayoutConstants.LOGO_LABEL_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "logo.png"));
        logoLabel.setIcon(icon);

        this.headerPanel.add(logoLabel);
    }

    protected void setTitlePane() {
        JEditorPane titlePane = new JEditorPane();

        int x = LayoutConstants.LOGO_LABEL_WIDTH + LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        titlePane.setBounds(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT);

        titlePane.setEditable(false);
        titlePane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".title { font-family: Arial; font-size: 24px; font-weight: bold; color: #ffffff; }");
        titlePane.setEditorKit(kit);
        titlePane.setText("<div class='title'>" + GlobalConstants.APPLICATION_NAME + "</div>");

        this.headerPanel.add(titlePane);
    }

    protected void setExitApplicationButton() {
        MButton exitApplicationButton = new MButton();

        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - EXIT_APPLICATION_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        exitApplicationButton.setBounds(x, y, EXIT_APPLICATION_BUTTON_WIDTH, EXIT_APPLICATION_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit_application.png"));
        exitApplicationButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit_application_hi.png"));
        exitApplicationButton.setRolloverIcon(rolloverIcon);
        exitApplicationButton.setText(null);
        exitApplicationButton.setMargin(new Insets(0, 0, 0, 0));
        exitApplicationButton.setBorder(null);
        exitApplicationButton.setBorderPainted(false);
        exitApplicationButton.setFocusPainted(false);
        exitApplicationButton.setContentAreaFilled(false);

        exitApplicationButton.setActionCommand("doExitApplication");
        exitApplicationButton.addActionListener(this);

        this.headerPanel.add(exitApplicationButton);
    }

    /**************************************************
     * Set Body Panel
     **************************************************/

    protected void setBodyPanel() {
        int y = LayoutConstants.HEADER_PANEL_HEIGHT;
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        Rectangle bounds = new Rectangle(0, y, this.getWidth(), height);
        this.bodyPanel = new BodyPanel(bounds);

        this.setSloganPane();
        this.setTestHomeButton();
        this.setPracticeHomeButton();

        this.getContentPane().add(this.bodyPanel);
    }

    protected void setSloganPane() {
        this.sloganPane = new JEditorPane();

        int x = (this.bodyPanel.getWidth() - SLOGAN_PANE_WIDTH) / 2;
        int y = LayoutConstants.MARGIN * 5;
        this.sloganPane.setBounds(x, y, SLOGAN_PANE_WIDTH, SLOGAN_PANE_HEIGHT);

        this.sloganPane.setEditable(false);
        this.sloganPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".slogan { color: #666666; font-family: Arial; font-size: 24px; font-weight: bold; text-align: center; } .slogan-desc { color: #999999; font-family: Arial; font-size: 16px; font-weight: normal; margin-top: 10px; text-align: center; } span.highlighted { color: #333333; } a.author { color: #333333; }");
        this.sloganPane.setEditorKit(kit);
        this.sloganPane.setText("<div class='slogan'>" + GlobalConstants.APPLICATION_NAME + " is a TOEFL Practice Offline Application</div>" +
                "<div class='slogan-desc'> only for <span class='highlighted'>noncommercial</span> use. Please contact <a href='' class='author'>us</a> to report bugs and check updates.</div>");
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

        this.bodyPanel.add(this.sloganPane);
    }

    protected void setTestHomeButton() {
        this.testHomeButton = new MButton();

        int x = (this.bodyPanel.getWidth() - MODULE_BUTTON_WIDTH * 2 - LayoutConstants.MARGIN * 10) / 2;
        int y = this.sloganPane.getY() + this.sloganPane.getHeight() + LayoutConstants.MARGIN * 20;
        this.testHomeButton.setBounds(x, y, MODULE_BUTTON_WIDTH, MODULE_BUTTON_HEIGHT);

        this.testHomeButton.setText("MODEL TESTS");
        this.testHomeButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.testHomeButton.setForeground(new Color(102, 102, 102)); // #666666

        this.testHomeButton.setActionCommand("goToTestHome");
        this.testHomeButton.addActionListener(this);

        this.bodyPanel.add(testHomeButton);
    }

    protected void setPracticeHomeButton() {
        this.practiceHomeButton = new MButton();

        int x = (this.bodyPanel.getWidth() + LayoutConstants.MARGIN * 10) / 2;
        int y = this.testHomeButton.getY();
        this.practiceHomeButton.setBounds(x, y, MODULE_BUTTON_WIDTH, MODULE_BUTTON_HEIGHT);

        this.practiceHomeButton.setText("PRACTICES");
        this.practiceHomeButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.practiceHomeButton.setForeground(new Color(102, 102, 102)); // #666666

        this.practiceHomeButton.setActionCommand("goToPracticeHome");
        this.practiceHomeButton.addActionListener(this);

        this.bodyPanel.add(practiceHomeButton);
    }

    /**************************************************
     * Set Footer Panel
     **************************************************/

    protected void setFooterPanel() {
        this.footerPanel = new FooterPanel();

        int y = this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT;
        this.footerPanel.setBounds(0, y, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);

        this.footerPanel.setLayout(null);

        this.setCopyrightPane();

        this.getContentPane().add(this.footerPanel);
    }

    protected void setCopyrightPane() {
        JEditorPane copyrightPane = new JEditorPane();

        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        copyrightPane.setBounds(x, y, LayoutConstants.COPYRIGHT_PANE_WIDTH, LayoutConstants.COPYRIGHT_PANE_HEIGHT);

        copyrightPane.setEditable(false);
        copyrightPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".copyright { color: #ffffff; font-family: Arial; font-size: 8px; font-weight: bold; text-align: center; }");
        copyrightPane.setEditorKit(kit);
        copyrightPane.setText("<div class='copyright'>Copyright 2006, 2010, 2011 by Educational Testing Service. All rights reserved. EDUCATIONAL TESTING SERVICE, ETS, the ETS logo, TOEFL and TOEFL iBT are registered trademarks of Educational Testing Service (ETS) in the United States and other countries.</div>");

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
        } else if (("goToTestHome").equals(e.getActionCommand())) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice device = ge.getDefaultScreenDevice();
                    testHomeFrame = new TestHomeFrame(device.getDefaultConfiguration(), MainFrame.this, GlobalConstants.APPLICATION_NAME);
                    device.setFullScreenWindow(testHomeFrame);
                    testHomeFrame.setVisible(true);
                    setVisible(false);
                }
            });
        } else if (("goToPracticeHome").equals(e.getActionCommand())) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice device = ge.getDefaultScreenDevice();
                    practiceHomeFrame = new PracticeHomeFrame(device.getDefaultConfiguration(), MainFrame.this, GlobalConstants.APPLICATION_NAME);
                    device.setFullScreenWindow(practiceHomeFrame);
                    practiceHomeFrame.setVisible(true);
                    setVisible(false);
                }
            });
        }
    }

    /**************************************************
     * Actions
     **************************************************/
}
