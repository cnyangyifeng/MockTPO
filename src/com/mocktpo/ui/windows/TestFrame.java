package com.mocktpo.ui.windows;

import com.mocktpo.ui.dialogs.PauseDialog;
import com.mocktpo.ui.tests.listening.ConversationPanel;
import com.mocktpo.ui.tests.listening.HeadsetPanel;
import com.mocktpo.ui.tests.listening.ListeningSectionDirectionsPanel;
import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.ui.widgets.FooterPanel;
import com.mocktpo.ui.widgets.HeaderPanel;
import com.mocktpo.ui.widgets.MButton;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestFrame extends JFrame implements ActionListener {

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

        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);

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
        style.addRule(".title { font-family: Arial; font-size: 11px; font-weight: bold; color: #ffffff; margin-top: 3px; }");
        this.titlePane.setEditorKit(kit);
        this.titlePane.setText("<div class='title'>TOEFL iBT Complete<br />Practice Test V25 Listening</div>");
    }

    private void setPauseTestButton() {
        this.pauseTestButton = new MButton();

        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.PAUSE_TEST_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        this.pauseTestButton.setBounds(x, y, LayoutConstants.PAUSE_TEST_BUTTON_WIDTH, LayoutConstants.PAUSE_TEST_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "pause_test.png"));
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
        this.sectionExitButton = new MButton();

        int x = this.pauseTestButton.getX() + LayoutConstants.PAUSE_TEST_BUTTON_WIDTH + LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.SECTION_EXIT_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        this.sectionExitButton.setBounds(x, y, LayoutConstants.SECTION_EXIT_BUTTON_WIDTH, LayoutConstants.SECTION_EXIT_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "section_exit.png"));
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
        this.continueButton = new MButton();

        int x = this.getWidth() - LayoutConstants.CONTINUE_BUTTON_WIDTH - LayoutConstants.MARGIN;
        int y = LayoutConstants.MARGIN * 2;
        this.continueButton.setBounds(x, y, LayoutConstants.CONTINUE_BUTTON_WIDTH, LayoutConstants.CONTINUE_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_ROOT + "continue.png"));
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
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        this.bodyBounds = new Rectangle(0, LayoutConstants.HEADER_PANEL_HEIGHT, this.getWidth(), height);
        this.bodyPanel = new BodyPanel(this.bodyBounds);
        this.headsetPanel = new HeadsetPanel(this.bodyBounds);
        this.bodyPanel = this.headsetPanel;
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
     * Public methods
     **************************************************/

    public void didPauseTest() {
        if (bodyPanel instanceof HeadsetPanel) {

        } else if (bodyPanel instanceof ListeningSectionDirectionsPanel) {
            this.lsdPanel.stopAudio();
        } else if (bodyPanel instanceof ConversationPanel) {
            this.conversationPanel.stopAudio();
        }
    }

    /**************************************************
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        switch (ac) {
            case "doPauseTest":
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        PauseDialog pause = new PauseDialog(TestFrame.this, "", true);
                        pause.setVisible(true);
                    }
                });
                break;
            case "doSectionExit":
                break;
            case "doContinue":
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        getContentPane().remove(bodyPanel);
                        if (bodyPanel instanceof HeadsetPanel) {
                            lsdPanel = new ListeningSectionDirectionsPanel(bodyBounds);
                            bodyPanel = lsdPanel;
                            lsdPanel.startAudio();
                        } else if (bodyPanel instanceof ListeningSectionDirectionsPanel) {
                            lsdPanel.stopAudio();
                            conversationPanel = new ConversationPanel(bodyBounds);
                            bodyPanel = conversationPanel;
                            conversationPanel.startAudio();
                        }
                        getContentPane().add(bodyPanel);
                        repaint();
                    }
                });
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
    private MButton pauseTestButton;
    private MButton sectionExitButton;
    private MButton continueButton;

    private BodyPanel bodyPanel;
    private HeadsetPanel headsetPanel;
    private ListeningSectionDirectionsPanel lsdPanel;
    private ConversationPanel conversationPanel;

    private FooterPanel footerPanel;
    private JEditorPane copyrightPane;

    private Rectangle bodyBounds;

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }
}
