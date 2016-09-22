package com.mocktpo.view.dialogs;

import com.mocktpo.util.FontsConstants;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import com.mocktpo.view.widgets.DialogBodyPanel;
import com.mocktpo.view.widgets.ImageButton;
import com.mocktpo.view.widgets.StyledLabelPane;
import com.mocktpo.view.window.AppWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactUsDialog extends MyDialog implements ActionListener {

    /* Constants */

    public static final int DIALOG_WIDTH = 440;
    public static final int DIALOG_HEIGHT = 420;

    public static final int TITLE_LABEL_WIDTH = 200;
    public static final int TITLE_LABEL_HEIGHT = 40;

    public static final int DESCRIPTION_PANE_WIDTH = 418;
    public static final int DESCRIPTION_PANE_HEIGHT = 270;

    public static final int QRCODE_IMAGE_LABEL_WIDTH = 180;
    public static final int QRCODE_IMAGE_LABEL_HEIGHT = 180;

    public static final int CLOSE_BUTTON_WIDTH = 74;
    public static final int CLOSE_BUTTON_HEIGHT = 34;

    /**************************************************
     * Components
     **************************************************/

    protected DialogBodyPanel bodyPanel;
    protected JLabel titleLabel;
    protected StyledLabelPane descriptionPane;
    protected JLabel qrcodeImageLabel;
    protected ImageButton closeButton;

    /**************************************************
     * Constructors
     **************************************************/

    public ContactUsDialog(AppWindow owner, String title, boolean modal) {
        super(owner, title, modal);
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
    }

    /**************************************************
     * Global Settings
     **************************************************/

    protected void globalSettings() {
        /* Set bounds */
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int x = ((int) screenSize.getWidth() - DIALOG_WIDTH) / 2;
        int y = ((int) screenSize.getHeight() - DIALOG_HEIGHT) / 2;
        this.setBounds(x, y, DIALOG_WIDTH, DIALOG_HEIGHT);
        /* Set unresizable and dispose on close */
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        /* Set title */
        this.setTitle("");
    }

    protected void setBodyPanel() {
        this.bodyPanel = new DialogBodyPanel();
        this.bodyPanel.setBounds(0, 0, this.getWidth(), this.getHeight());

        this.bodyPanel.setLayout(null);

        this.setTitleLabel();
        this.setDescriptionPane();
        this.setQRCodeImageLabel();
        this.setCloseButton();

        this.setContentPane(this.bodyPanel);
    }

    protected void setTitleLabel() {
        this.titleLabel = new JLabel("Contact Us", JLabel.CENTER);
        this.titleLabel.setForeground(Color.WHITE);
        this.titleLabel.setFont(new Font(FontsConstants.SYSTEM_FONT, Font.BOLD, 16));
        int x = (this.bodyPanel.getWidth() - TITLE_LABEL_WIDTH) / 2;
        this.titleLabel.setBounds(x, LayoutConstants.MARGIN, TITLE_LABEL_WIDTH, TITLE_LABEL_HEIGHT);

        this.bodyPanel.add(this.titleLabel);
    }

    protected void setDescriptionPane() {
        /* Initialize component */
        int x = (this.bodyPanel.getWidth() - DESCRIPTION_PANE_WIDTH) / 2;
        int y = this.titleLabel.getY() + TITLE_LABEL_HEIGHT + LayoutConstants.MARGIN * 2;
        String css = ".desc { background-color: #ffffff; color: #333333; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 12px; height: " + DESCRIPTION_PANE_HEIGHT + "px; padding: 10px; }";
        String html = "<div class='desc'>Please open WeChat on phone and scan QR Code to follow us if any technical support is required.<br />";
        this.descriptionPane = new StyledLabelPane(x, y, DESCRIPTION_PANE_WIDTH, DESCRIPTION_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.bodyPanel.add(this.descriptionPane, 2);
    }

    private void setQRCodeImageLabel() {
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "qrcode.png"));

        this.qrcodeImageLabel = new JLabel(icon);
        this.qrcodeImageLabel.setForeground(Color.WHITE);
        this.qrcodeImageLabel.setFont(new Font(FontsConstants.SYSTEM_FONT, Font.BOLD, 16));
        int x = (this.bodyPanel.getWidth() - QRCODE_IMAGE_LABEL_WIDTH) / 2;
        this.qrcodeImageLabel.setBounds(x, 125, QRCODE_IMAGE_LABEL_WIDTH, QRCODE_IMAGE_LABEL_HEIGHT);

        this.bodyPanel.add(this.qrcodeImageLabel, 1);
    }

    protected void setCloseButton() {
        /* Initialize component */
        int x = (this.bodyPanel.getWidth() - CLOSE_BUTTON_WIDTH) / 2;
        int y = this.descriptionPane.getY() + DESCRIPTION_PANE_HEIGHT + LayoutConstants.MARGIN * 4;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "close.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "close_hi.png"));
        this.closeButton = new ImageButton(x, y, CLOSE_BUTTON_WIDTH, CLOSE_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.closeButton.setActionCommand("doClose");
        this.closeButton.addActionListener(this);
        /* Add to the parent component */
        this.bodyPanel.add(this.closeButton);
    }

    /**************************************************
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        if ("doClose".equals(e.getActionCommand())) {
            this.dispose();
        }
    }
}
