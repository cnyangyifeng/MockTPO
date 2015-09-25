package com.mocktpo.ui.windows;

import com.mocktpo.MApplication;
import com.mocktpo.model.MTest;
import com.mocktpo.model.MockTPO;
import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.ui.widgets.FooterPanel;
import com.mocktpo.ui.widgets.HeaderPanel;
import com.mocktpo.ui.widgets.MButton;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import com.thoughtworks.xstream.XStream;
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
import java.io.File;
import java.net.URL;
import java.util.List;

public class MainFrame extends JFrame implements ActionListener, HyperlinkListener {

    private static final Logger logger = LogManager.getLogger();

    public static final int EXIT_APPLICATION_BUTTON_WIDTH = 84;
    public static final int EXIT_APPLICATION_BUTTON_HEIGHT = 34;
    public static final int SLOGAN_PANE_WIDTH = 1000;
    public static final int SLOGAN_PANE_HEIGHT = 80;
    public static final int BODY_TABLE_PANE_WIDTH = 1000;

    private TestFrame testFrame;
    private HeaderPanel headerPanel;
    private JLabel logoLabel;
    private JEditorPane titlePane;
    private MButton exitApplicationButton;
    private BodyPanel bodyPanel;
    private JEditorPane sloganPane;
    private JScrollPane bodyScrollPane;
    private FooterPanel footerPanel;
    private JEditorPane copyrightPane;

    private MockTPO tpo;

    public MainFrame(GraphicsConfiguration gc) {
        super(gc);
        initComponents();
    }

    private void initComponents() {
        this.globalSettings();

        this.setLayout(null);

        this.setHeaderPanel();
        this.setBodyPanel();
        this.setFooterPanel();

        this.getContentPane().add(this.headerPanel);
        this.getContentPane().add(this.bodyPanel);
        this.getContentPane().add(this.footerPanel);
    }

    private void globalSettings() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        Rectangle bounds = new Rectangle(screenSize);
        this.setBounds(bounds);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setUndecorated(true);

        this.setTitle("MockTPO");
    }

    /**************************************************
     * Set Header Panel
     **************************************************/

    private void setHeaderPanel() {
        this.headerPanel = new HeaderPanel();

        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);

        this.headerPanel.setLayout(null);

        this.setLogoLabel();
        this.setTitlePane();
        this.setExitApplicationButton();

        this.headerPanel.add(this.logoLabel);
        this.headerPanel.add(this.titlePane);
        this.headerPanel.add(this.exitApplicationButton);
    }

    private void setLogoLabel() {
        this.logoLabel = new JLabel();

        this.logoLabel.setBounds(0, LayoutConstants.MARGIN, LayoutConstants.LOGO_LABEL_WIDTH, LayoutConstants.LOGO_LABEL_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "logo.png"));
        this.logoLabel.setIcon(icon);
    }

    private void setTitlePane() {
        this.titlePane = new JEditorPane();

        int x = LayoutConstants.LOGO_LABEL_WIDTH + LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        this.titlePane.setBounds(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT);

        this.titlePane.setEditable(false);
        this.titlePane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".title { font-family: Arial; font-size: 24px; font-weight: bold; color: #ffffff; }");
        this.titlePane.setEditorKit(kit);
        this.titlePane.setText("<div class='title'>MockTPO</div>");
    }

    private void setExitApplicationButton() {
        this.exitApplicationButton = new MButton();

        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - EXIT_APPLICATION_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        this.exitApplicationButton.setBounds(x, y, EXIT_APPLICATION_BUTTON_WIDTH, EXIT_APPLICATION_BUTTON_HEIGHT);
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "exit_application.png"));

        this.exitApplicationButton.setIcon(icon);
        this.exitApplicationButton.setText(null);
        this.exitApplicationButton.setMargin(new Insets(0, 0, 0, 0));
        this.exitApplicationButton.setBorder(null);
        this.exitApplicationButton.setBorderPainted(false);
        this.exitApplicationButton.setFocusPainted(false);
        this.exitApplicationButton.setContentAreaFilled(false);

        this.exitApplicationButton.setActionCommand("doExitApplication");
        this.exitApplicationButton.addActionListener(this);
    }

    /**************************************************
     * Set Body Panel
     **************************************************/

    private void setBodyPanel() {
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        Rectangle bounds = new Rectangle(0, LayoutConstants.HEADER_PANEL_HEIGHT, this.getWidth(), height);
        this.bodyPanel = new BodyPanel(bounds);

        this.setSloganPane();
        this.setBodyScrollPane();

        this.bodyPanel.add(this.sloganPane);
        this.bodyPanel.add(this.bodyScrollPane);
    }

    private void setSloganPane() {
        this.sloganPane = new JEditorPane();

        int x = (this.bodyPanel.getWidth() - SLOGAN_PANE_WIDTH) / 2;
        int y = LayoutConstants.MARGIN * 5;
        this.sloganPane.setBounds(x, y, SLOGAN_PANE_WIDTH, SLOGAN_PANE_HEIGHT);

        this.sloganPane.setEditable(false);
        this.sloganPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".slogan { color: #666666; font-family: Arial; font-size: 24px; font-weight: bold; text-align: center; } .slogan-desc { color: #999999; font-family: Arial; font-size: 16px; font-weight: normal; margin-top: 10px; text-align: center; } span.highlighted { color: #333333; } a.author { color: #333333; } ");
        this.sloganPane.setEditorKit(kit);
        this.sloganPane.setText("<div class='slogan'>MockTPO is a TOEFL Practice Offline Application</div" +
                "<div class='slogan-desc'> only for <span class='highlighted'>noncommercial</span> use. Please contact <a href='' class='author'>me</a> to report bugs and check updates.</div>");
    }

    private void setBodyScrollPane() {
        this.bodyScrollPane = new JScrollPane();

        int x = this.sloganPane.getX();
        int y = this.sloganPane.getY() + this.sloganPane.getHeight() + LayoutConstants.MARGIN * 5;
        int height = this.bodyPanel.getHeight() - y - LayoutConstants.MARGIN * 10;
        this.bodyScrollPane.setBounds(x, y, BODY_TABLE_PANE_WIDTH, height);

        JEditorPane bodyPane = new JEditorPane();

        bodyPane.setEditable(false);
        bodyPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule("table { font-family: Georgia; font-size: 14px; font-weight: normal; margin-bottom: 20px; width: 100%; } tr { border-bottom: 1px #dddddd dashed; } tr.local { color: #333333; } tr.web { color: #999999; } td { height: 50px; margin-left: 10px; } a { text-decoration: none; color: #3c4d82; } a.reload { color: #333333; }");
        bodyPane.setEditorKit(kit);

        try {
            XStream xs = new XStream();
            xs.alias("mocktpo", MockTPO.class);
            xs.alias("test", MTest.class);
            String val = GlobalConstants.TESTS_ROOT + GlobalConstants.MOCKTPO_FILE;
            URL xml = this.getClass().getResource(val);
            this.tpo = (MockTPO) xs.fromXML(new File(xml.toURI()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<MTest> tests = this.tpo.getTests();

        String val = "<table>";
        for (MTest test : tests) {
            if ("local".equals(test.getStatus())) {
                val += "<tr class='local'><td>" + test.getName() + "</td>";
                val += "<td><a class='reload' href='reload'>100%</a></td>";
                val += "<td><a href='new#" + test.getIndex() + "'>New Test</a></td>";
                val += "<td><a class='local' href='continue#" + test.getIndex() + "'>Continue</a></td>";
                val += "<td><a href='reports'>Reports</a></td></tr>";
            } else if ("web".equals(test.getStatus())) {
                val += "<tr class='web'><td>" + test.getName() + "</td>";
                val += "<td><a href='" + "download" + "'>Download</a></td></tr>";
            }
        }
        val += "</table>";
        bodyPane.setText(val);

        bodyPane.addHyperlinkListener(this);

        this.bodyScrollPane.setViewportView(bodyPane);
    }

    /**************************************************
     * Set Footer Panel
     **************************************************/

    private void setFooterPanel() {
        this.footerPanel = new FooterPanel();

        this.footerPanel.setBounds(0, this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);

        this.footerPanel.setLayout(null);

        this.setCopyrightPane();

        this.footerPanel.add(this.copyrightPane);
    }

    private void setCopyrightPane() {
        this.copyrightPane = new JEditorPane();

        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        this.copyrightPane.setBounds(x, y, LayoutConstants.COPYRIGHT_PANE_WIDTH, LayoutConstants.COPYRIGHT_PANE_HEIGHT);

        this.copyrightPane.setEditable(false);
        this.copyrightPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".copyright { color: #ffffff; font-family: Arial; font-size: 8px; font-weight: bold; text-align: center; }");
        this.copyrightPane.setEditorKit(kit);
        this.copyrightPane.setText("<div class='copyright'>Copyright 2006, 2010, 2011 by Educational Testing Service. All rights reserved. EDUCATIONAL TESTING SERVICE, ETS, the ETS logo, TOEFL and TOEFL iBT are registered trademarks of Educational Testing Service (ETS) in the United States and other countries.</div>");
    }

    /**************************************************
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        if ("doExitApplication".equals(e.getActionCommand())) {
            System.exit(0);
        }
    }

    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            String action = e.getDescription();
            switch (action) {
                case "reload":
                    break;
                case "download":
                    break;
                default:
                    String[] arr = action.split("#");
                    if (arr[0].equals("new")) {
                        logger.info("New Test: {}", arr[1]);
                        MApplication.settings.put("testIndex", arr[1]);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                                GraphicsDevice device = ge.getDefaultScreenDevice();
                                testFrame = new TestFrame(device.getDefaultConfiguration(), MainFrame.this);
                                device.setFullScreenWindow(testFrame);
                                testFrame.setVisible(true);
                                setVisible(false);
                            }
                        });
                    } else if (arr[0].equals("continue")) {
                        logger.info("Continue Test: {}", arr[1]);
                    }
                    break;
            }
        }
    }
}
