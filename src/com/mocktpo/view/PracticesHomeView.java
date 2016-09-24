package com.mocktpo.view;

import com.mocktpo.listener.ExitApplicationListener;
import com.mocktpo.listener.ToIndexViewListener;
import com.mocktpo.util.FontsConstants;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import com.mocktpo.widget.button.ImageButton;
import com.mocktpo.widget.panel.BodyPanel;
import com.mocktpo.widget.panel.FooterPanel;
import com.mocktpo.widget.panel.HeaderPanel;
import com.mocktpo.widget.panel.StyledLabelPane;
import com.mocktpo.window.AppWindow;

import javax.swing.*;
import java.awt.*;

public class PracticesHomeView extends MyView {

    /* Constants */

    public static final int EXIT_APPLICATION_BUTTON_WIDTH = 84;
    public static final int EXIT_APPLICATION_BUTTON_HEIGHT = 34;
    public static final int GO_BACK_HOME_BUTTON_WIDTH = 84;
    public static final int GO_BACK_HOME_BUTTON_HEIGHT = 34;
    public static final int SLOGAN_PANE_WIDTH = 1000;
    public static final int SLOGAN_PANE_HEIGHT = 80;

    /* Components */

    private HeaderPanel headerPanel;
    private BodyPanel bodyPanel;
    private FooterPanel footerPanel;

    private ImageButton exitApplicationButton;
    private ImageButton goBackHomeButton;
    private StyledLabelPane sloganPane;

    /**************************************************
     * Constructors
     **************************************************/

    public PracticesHomeView(AppWindow owner) {
        super(owner);
        this.configData();
        this.initComponents();
    }


    /**************************************************
     * Configure Data
     **************************************************/

    private void configData() {
    }

    /**************************************************
     * Components Initialization
     **************************************************/

    private void initComponents() {
        /* Set layout */
        this.setLayout(null);
        /* Set components */
        this.setBodyPanel();
        this.setHeaderPanel();
        this.setFooterPanel();
    }

    /**************************************************
     * Header Panel Settings
     **************************************************/

    private void setHeaderPanel() {
        /* Initialize component */
        this.headerPanel = new HeaderPanel();
        /* Set bounds */
        this.headerPanel.setBounds(0, 0, this.getWidth(), LayoutConstants.HEADER_PANEL_HEIGHT);
        /* Set layouts */
        this.headerPanel.setLayout(null);
        /* Set children components */
        this.setTitlePane();
        this.setExitApplicationButton();
        this.setGoBackHomeButton();
        /* Add to the parent component */
        this.add(this.headerPanel);
    }

    private void setTitlePane() {
        /* Initialize component */
        int x = LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN;
        String css = ".title { font-family: " + FontsConstants.LOGO_FONT + "; font-size: 20px; font-weight: bold; color: #ffffff; }";
        String html = "<div class='title'>" + GlobalConstants.PRACTICES_HOME_TITLE + "</div>";
        StyledLabelPane titlePane = new StyledLabelPane(x, y, LayoutConstants.TITLE_PANE_WIDTH, LayoutConstants.TITLE_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.headerPanel.add(titlePane);
    }

    private void setExitApplicationButton() {
        /* Initialize component */
        int x = LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - EXIT_APPLICATION_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit_application.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit_application_hi.png"));
        this.exitApplicationButton = new ImageButton(x, y, EXIT_APPLICATION_BUTTON_WIDTH, EXIT_APPLICATION_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.exitApplicationButton.addActionListener(new ExitApplicationListener(this.getAppWindow()));
        /* Add to the parent component */
        this.headerPanel.add(this.exitApplicationButton);
    }

    private void setGoBackHomeButton() {
        /* Initialize component */
        int x = this.exitApplicationButton.getX() + EXIT_APPLICATION_BUTTON_WIDTH + LayoutConstants.MARGIN;
        int y = LayoutConstants.HEADER_PANEL_HEIGHT - GO_BACK_HOME_BUTTON_HEIGHT - LayoutConstants.MARGIN;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "go_back_home.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "go_back_home_hi.png"));
        this.goBackHomeButton = new ImageButton(x, y, GO_BACK_HOME_BUTTON_WIDTH, GO_BACK_HOME_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.goBackHomeButton.addActionListener(new ToIndexViewListener(this.getAppWindow()));
        /* Add to the parent component */
        this.headerPanel.add(this.goBackHomeButton);
    }

    /**************************************************
     * Body Panel Settings
     **************************************************/

    private void setBodyPanel() {
        /* Initialize component */
        int height = this.getHeight() - LayoutConstants.HEADER_PANEL_HEIGHT - LayoutConstants.FOOTER_PANEL_HEIGHT;
        Rectangle bounds = new Rectangle(0, LayoutConstants.HEADER_PANEL_HEIGHT, this.getWidth(), height);
        this.bodyPanel = new BodyPanel(bounds);
        /* Set children components */
        this.setSloganPane();
        this.setBodyScrollPane();
        /* Add to the parent component */
        this.add(this.bodyPanel);
    }

    private void setSloganPane() {
        /* Initialize component */
        int x = (this.bodyPanel.getWidth() - SLOGAN_PANE_WIDTH) / 2;
        int y = LayoutConstants.MARGIN * 12;
        String css = ".slogan { color: #333333; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 24px; text-align: center; }";
        String html = "<div class='slogan'>" + GlobalConstants.PRACTICES_HOME_TITLE + "</div>";
        this.sloganPane = new StyledLabelPane(x, y, SLOGAN_PANE_WIDTH, SLOGAN_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.bodyPanel.add(this.sloganPane);
    }

    private void setBodyScrollPane() {
        // TODO Set body scroll pane
    }

    /**************************************************
     * Footer Panel Settings
     **************************************************/

    private void setFooterPanel() {
        /* Initialize component */
        this.footerPanel = new FooterPanel();
        /* Set bounds */
        this.footerPanel.setBounds(0, this.getHeight() - LayoutConstants.FOOTER_PANEL_HEIGHT, this.getWidth(), LayoutConstants.FOOTER_PANEL_HEIGHT);
        /* Set layout */
        this.footerPanel.setLayout(null);
        /* Set children components */
        this.setCopyrightPane();
        /* Add to the parent component */
        this.add(this.footerPanel);
    }

    private void setCopyrightPane() {
        /* Initialize component */
        int x = (this.footerPanel.getWidth() - LayoutConstants.COPYRIGHT_PANE_WIDTH) / 2;
        int y = (LayoutConstants.FOOTER_PANEL_HEIGHT - LayoutConstants.COPYRIGHT_PANE_HEIGHT) / 2;
        String css = ".copyright { color: #ffffff; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 8px; font-weight: bold; text-align: center; }";
        String html = "<div class='copyright'>" + GlobalConstants.COPYRIGHT_INFO + "</div>";
        StyledLabelPane copyrightPane = new StyledLabelPane(x, y, LayoutConstants.COPYRIGHT_PANE_WIDTH, LayoutConstants.COPYRIGHT_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.footerPanel.add(copyrightPane);
    }
}
