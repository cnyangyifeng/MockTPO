package com.mocktpo.ui;

import com.mocktpo.util.Constants;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TestFrame extends JFrame implements WindowListener, ActionListener {

    public TestFrame(GraphicsConfiguration gc, MainFrame mainFrame) {
        super(gc);
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents() {
        this.globalSettings();

        this.setLayout(null);

        this.setHeaderPanel();
        this.setBodyPanel();
        this.setFooterPanel();

        this.getContentPane().add(headerPanel);
        this.getContentPane().add(bodyPanel);
        this.getContentPane().add(footerPanel);

        this.addWindowListener(this);
    }

    private void globalSettings() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        // Insets insets = tk.getScreenInsets(this.getGraphicsConfiguration());
        // System.out.println("Insets: left " + insets.left + ", top " + insets.top + ", right " + insets.right + ", bottom " + insets.bottom);

        Rectangle bounds = new Rectangle(screenSize);
        // bounds.width -= insets.left + insets.right;
        // bounds.height -= insets.top + insets.bottom;
        this.setBounds(bounds);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setUndecorated(true);

        this.setTitle("TOEFL iBT Complete Practice Test");
    }

    /**************************************************
     * Set Header Panel
     **************************************************/

    private void setHeaderPanel() {
        this.headerPanel = new HeaderPanel();

        this.headerPanel.setBounds(0, 0, this.getWidth(), Constants.HEADER_PANEL_HEIGHT);

        this.headerPanel.setLayout(null);

        this.setLogoLabel();
        this.setTitlePane();
        this.setPauseTestButton();
        this.setSectionExitButton();
        this.setContinueButton();

        this.headerPanel.add(this.logoLabel);
        this.headerPanel.add(this.titlePane);
        this.headerPanel.add(this.pauseTestButton);
        this.headerPanel.add(this.sectionExitButton);
        this.headerPanel.add(this.continueButton);
    }

    private void setLogoLabel() {
        this.logoLabel = new JLabel();

        this.logoLabel.setBounds(0, Constants.MARGIN, Constants.LOGO_LABEL_WIDTH, Constants.LOGO_LABEL_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("logo.png"));
        this.logoLabel.setIcon(icon);
    }

    private void setTitlePane() {
        this.titlePane = new JEditorPane();

        int x = Constants.LOGO_LABEL_WIDTH + Constants.MARGIN * 2;
        int y = Constants.MARGIN;
        this.titlePane.setBounds(x, y, Constants.TITLE_PANE_WIDTH, Constants.TITLE_PANE_HEIGHT);

        this.titlePane.setEditable(false);
        this.titlePane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".title { font-family: Arial; font-size: 11px; font-weight: bold; color: #ffffff; margin-top: 3px; }");
        this.titlePane.setEditorKit(kit);
        this.titlePane.setText("<div class='title'>TOEFL iBT Complete<br />Practice Test V25 Listening</div>");
    }

    private void setPauseTestButton() {
        this.pauseTestButton = new JButton();

        int x = Constants.MARGIN;
        int y = Constants.HEADER_PANEL_HEIGHT - Constants.PAUSE_TEST_BUTTON_HEIGHT - Constants.MARGIN;
        this.pauseTestButton.setBounds(x, y, Constants.PAUSE_TEST_BUTTON_WIDTH, Constants.PAUSE_TEST_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("pause_test.png"));
        this.pauseTestButton.setIcon(icon);
        this.pauseTestButton.setText(null);
        this.pauseTestButton.setMargin(new Insets(0, 0, 0, 0));
        this.pauseTestButton.setBorder(null);
        this.pauseTestButton.setBorderPainted(false);
        this.pauseTestButton.setFocusPainted(false);
        this.pauseTestButton.setContentAreaFilled(false);

        this.pauseTestButton.setActionCommand("doPauseTest");
        this.pauseTestButton.addActionListener(this);
    }

    private void setSectionExitButton() {
        this.sectionExitButton = new JButton();

        int x = this.pauseTestButton.getX() + Constants.PAUSE_TEST_BUTTON_WIDTH + Constants.MARGIN;
        int y = Constants.HEADER_PANEL_HEIGHT - Constants.SECTION_EXIT_BUTTON_HEIGHT - Constants.MARGIN;
        this.sectionExitButton.setBounds(x, y, Constants.SECTION_EXIT_BUTTON_WIDTH, Constants.SECTION_EXIT_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("section_exit.png"));
        this.sectionExitButton.setIcon(icon);
        this.sectionExitButton.setText(null);
        this.sectionExitButton.setMargin(new Insets(0, 0, 0, 0));
        this.sectionExitButton.setBorder(null);
        this.sectionExitButton.setBorderPainted(false);
        this.sectionExitButton.setFocusPainted(false);
        this.sectionExitButton.setContentAreaFilled(false);

        this.sectionExitButton.setActionCommand("doSectionExit");
        this.sectionExitButton.addActionListener(this);
    }

    private void setContinueButton() {
        this.continueButton = new JButton();

        int x = this.getWidth() - Constants.CONTINUE_BUTTON_WIDTH - Constants.MARGIN;
        int y = Constants.MARGIN * 2;
        this.continueButton.setBounds(x, y, Constants.CONTINUE_BUTTON_WIDTH, Constants.CONTINUE_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("continue.png"));
        this.continueButton.setIcon(icon);
        this.continueButton.setText(null);
        this.continueButton.setMargin(new Insets(0, 0, 0, 0));
        this.continueButton.setBorder(null);
        this.continueButton.setBorderPainted(false);
        this.continueButton.setFocusPainted(false);
        this.continueButton.setContentAreaFilled(false);

        this.continueButton.setActionCommand("doContinue");
        this.continueButton.addActionListener(this);
    }

    /**************************************************
     * Set Body Panel
     **************************************************/

    private void setBodyPanel() {
        this.bodyPanel = new BodyPanel();

        int height = this.getHeight() - Constants.HEADER_PANEL_HEIGHT - Constants.FOOTER_PANEL_HEIGHT;
        this.bodyPanel.setBounds(0, Constants.HEADER_PANEL_HEIGHT, this.getWidth(), height);

        this.bodyPanel.setLayout(null);

        this.lsdPanel = new ListeningSectionDirectionsPanel(this.bodyPanel);
        this.lsdPanel.startAudio();

        this.bodyPanel.add(this.lsdPanel);
    }

    /**************************************************
     * Set Footer Panel
     **************************************************/

    private void setFooterPanel() {
        this.footerPanel = new FooterPanel();

        this.footerPanel.setBounds(0, this.getHeight() - Constants.FOOTER_PANEL_HEIGHT, this.getWidth(), Constants.FOOTER_PANEL_HEIGHT);

        this.footerPanel.setLayout(null);

        this.setCopyrightPane();

        this.footerPanel.add(this.copyrightPane);
    }

    private void setCopyrightPane() {
        this.copyrightPane = new JEditorPane();

        int x = (this.footerPanel.getWidth() - Constants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (Constants.FOOTER_PANEL_HEIGHT - Constants.COPYRIGHT_PANE_HEIGHT) / 2;
        this.copyrightPane.setBounds(x, y, Constants.COPYRIGHT_PANE_WIDTH, Constants.COPYRIGHT_PANE_HEIGHT);

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

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        if (this.lsdPanel != null) {
            this.lsdPanel.stopAudio();
        }

        String ac = e.getActionCommand();
        switch (ac) {
            case "doPauseTest":
                PauseDialog pause = new PauseDialog(this, "", true);
                pause.setVisible(true);
                break;
            case "doSectionExit":
                break;
            case "doContinue":
                this.conversationPanel = new ConversationPanel(this.bodyPanel);
                this.bodyPanel.remove(this.lsdPanel);
                this.bodyPanel.add(this.conversationPanel);
                this.bodyPanel.repaint();
                break;
            default:
                break;
        }
    }

    /**************************************************
     * Properties
     **************************************************/

    private MainFrame mainFrame;

    private HeaderPanel headerPanel;
    private JLabel logoLabel;
    private JEditorPane titlePane;
    private JButton pauseTestButton;
    private JButton sectionExitButton;
    private JButton continueButton;

    private BodyPanel bodyPanel;
    private ListeningSectionDirectionsPanel lsdPanel;
    private ConversationPanel conversationPanel;

    private FooterPanel footerPanel;
    private JEditorPane copyrightPane;

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }
}
