package com.mocktpo.ui.windows;

import com.mocktpo.ui.dialogs.ContactUsDialog;
import com.mocktpo.ui.widgets.*;
import com.mocktpo.util.FontsConstants;
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

public class PracticesHomeFrame extends JFrame implements ActionListener {

    /* Constants */

    public static final int GO_BACK_HOME_BUTTON_WIDTH = 84;
    public static final int GO_BACK_HOME_BUTTON_HEIGHT = 34;
    public static final int SLOGAN_PANE_WIDTH = 1000;
    public static final int SLOGAN_PANE_HEIGHT = 80;

    /* Logger */

    protected static final Logger logger = LogManager.getLogger();

    /* Frames */

    protected MainFrame mainFrame;

    /* Components */

    protected HeaderPanel headerPanel;
    protected BodyPanel bodyPanel;
    protected StyledLabelPane sloganPane;
    protected FooterPanel footerPanel;

    /* Variables */

    private String title;

    /**************************************************
     * Constructors
     **************************************************/

    public PracticesHomeFrame(GraphicsConfiguration gc, MainFrame mainFrame, String title) {
        super(gc);
        this.mainFrame = mainFrame;
        this.title = title;
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
        this.setTitle(this.title);
    }

    /**************************************************
     * Header Panel Settings
     **************************************************/

    protected void setHeaderPanel() {
        /* Initialize component */
        this.headerPanel = new HeaderPanel();
        /* Set bounds */
        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);
        /* Set layouts */
        this.headerPanel.setLayout(null);
        /* Set children components */
        this.setTitlePane();
        this.setGoBackHomeButton();
        /* Add to the parent component */
        this.getContentPane().add(this.headerPanel);
    }

    protected void setTitlePane() {
        /* Initialize component */
        int x = LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        String css = ".title { font-family: " + FontsConstants.LOGO_FONT + "; font-size: 20px; font-weight: bold; color: #ffffff; }";
        String html = "<div class='title'>" + GlobalConstants.APPLICATION_NAME + " " + GlobalConstants.PRACTICES_HOME_TITLE + "</div>";
        StyledLabelPane titlePane = new StyledLabelPane(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.headerPanel.add(titlePane);
    }

    protected void setGoBackHomeButton() {
        /* Initialize component */
        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - GO_BACK_HOME_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "go_back_home.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "go_back_home_hi.png"));
        ImageButton goBackHomeButton = new ImageButton(x, y, GO_BACK_HOME_BUTTON_WIDTH, GO_BACK_HOME_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        goBackHomeButton.setActionCommand("doGoBackHome");
        goBackHomeButton.addActionListener(this);
        /* Add to the parent component */
        this.headerPanel.add(goBackHomeButton);
    }

    /**************************************************
     * Body Panel Settings
     **************************************************/

    protected void setBodyPanel() {
        /* Initialize component */
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        Rectangle bounds = new Rectangle(0, LayoutConstants.HEADER_PANEL_HEIGHT, this.getWidth(), height);
        this.bodyPanel = new BodyPanel(bounds);
        /* Set children components */
        this.setSloganPane();
        this.setBodyScrollPane();
        /* Add to the parent component */
        this.getContentPane().add(this.bodyPanel);
    }

    protected void setSloganPane() {
        /* Initialize component */
        int x = (this.bodyPanel.getWidth() - SLOGAN_PANE_WIDTH) / 2;
        int y = LayoutConstants.MARGIN * 12;
        String css = ".slogan { color: #333333; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 24px; text-align: center; }";
        String html = "<div class='slogan'>TOEFL&reg; iBT " + GlobalConstants.PRACTICES_HOME_TITLE + "</div>";
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
                            ContactUsDialog contactUs = new ContactUsDialog(PracticesHomeFrame.this, "", true);
                            contactUs.setVisible(true);
                        }
                    });
                }
            }
        });
        /* Add to the parent component */
        this.bodyPanel.add(this.sloganPane);
    }

    protected void setBodyScrollPane() {
        // TODO
    }

    /**************************************************
     * Footer Panel Settings
     **************************************************/

    protected void setFooterPanel() {
        /* Initialize component */
        this.footerPanel = new FooterPanel();
        /* Set bounds */
        this.footerPanel.setBounds(0, this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);
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
        if ("doGoBackHome".equals(e.getActionCommand())) {
            logger.info("'Go Back Home' button pressed.");
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice device = ge.getDefaultScreenDevice();
                    MainFrame mainFrame = PracticesHomeFrame.this.getMainFrame();
                    /* Dispose current frame */
                    PracticesHomeFrame.this.dispose();
                    /* Show main frame */
                    if (mainFrame == null) {
                        mainFrame = new MainFrame(device.getDefaultConfiguration());
                    }
                    device.setFullScreenWindow(mainFrame);
                    mainFrame.setVisible(true);
                }
            });
        }
    }

    /**************************************************
     * Actions
     **************************************************/

    // TODO

    /**************************************************
     * Getters and setters
     **************************************************/

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }
}
