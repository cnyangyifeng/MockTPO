package com.mocktpo.ui.dialogs;

import com.mocktpo.ui.widgets.DialogBodyPanel;
import com.mocktpo.ui.widgets.MButton;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactUsDialog extends JDialog implements ActionListener {


    public static final int DIALOG_WIDTH = 440;
    public static final int DIALOG_HEIGHT = 420;

    public static final int TITLE_WIDTH = 200;
    public static final int TITLE_HEIGHT = 40;

    public static final int DESCRIPTION_PANE_WIDTH = 418;
    public static final int DESCRIPTION_PANE_HEIGHT = 260;

    public static final int CLOSE_BUTTON_WIDTH = 74;
    public static final int CLOSE_BUTTON_HEIGHT = 34;

    /**************************************************
     * Properties
     **************************************************/

    protected DialogBodyPanel bodyPanel;
    protected JLabel titleLabel;
    protected JEditorPane descriptionPane;
    protected MButton closeButton;

    public ContactUsDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.initComponents();
    }

    private void initComponents() {
        this.globalSettings();

        this.setLayout(null);

        this.setBodyPanel();

        this.getContentPane().add(this.bodyPanel);
    }

    protected void globalSettings() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        int x = ((int) screenSize.getWidth() - DIALOG_WIDTH) / 2;
        int y = ((int) screenSize.getHeight() - DIALOG_HEIGHT) / 2;
        this.setBounds(x, y, DIALOG_WIDTH, DIALOG_HEIGHT);

        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setTitle("");
    }

    protected void setBodyPanel() {
        this.bodyPanel = new DialogBodyPanel();
        this.bodyPanel.setBounds(0, 0, this.getWidth(), this.getHeight());

        this.bodyPanel.setLayout(null);

        this.setTitleLabel();
        this.setDescriptionPane();
        this.setCloseButton();
    }

    protected void setTitleLabel() {
        this.titleLabel = new JLabel("Contact Us", JLabel.CENTER);
        this.titleLabel.setForeground(Color.WHITE);
        this.titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        int x = (this.bodyPanel.getWidth() - TITLE_WIDTH) / 2;
        this.titleLabel.setBounds(x, LayoutConstants.MARGIN, TITLE_WIDTH, TITLE_HEIGHT);

        this.bodyPanel.add(this.titleLabel);
    }

    protected void setDescriptionPane() {
        this.descriptionPane = new JEditorPane();

        int x = (this.bodyPanel.getWidth() - DESCRIPTION_PANE_WIDTH) / 2;
        int y = this.titleLabel.getY() + TITLE_HEIGHT + LayoutConstants.MARGIN * 2;
        this.descriptionPane.setBounds(x, y, DESCRIPTION_PANE_WIDTH, DESCRIPTION_PANE_HEIGHT);

        this.descriptionPane.setEditable(false);
        this.descriptionPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".desc { background-color: #ffffff; font-family: Roboto; font-size: 12px; color: #333333; padding: 10px; } .desc-img-wrapper { margin-top: 10px; text-align: center; }");
        this.descriptionPane.setEditorKit(kit);
        String imgUrl = this.getClass().getResource(GlobalConstants.IMAGES_DIR + "qrcode.png").toString();
        String text = "<div class='desc'>Please open WeChat on phone and scan QR Code to follow us if any technical support is required.<br />";
        text += "<div class='desc-img-wrapper'><img src='" + imgUrl + "' /></div></div>";
        this.descriptionPane.setText(text);

        this.bodyPanel.add(this.descriptionPane);
    }

    protected void setCloseButton() {
        this.closeButton = new MButton();

        int x = (this.bodyPanel.getWidth() - CLOSE_BUTTON_WIDTH) / 2;
        int y = this.descriptionPane.getY() + DESCRIPTION_PANE_HEIGHT + LayoutConstants.MARGIN * 2;
        this.closeButton.setBounds(x, y, CLOSE_BUTTON_WIDTH, CLOSE_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "close.png"));
        this.closeButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "close_hi.png"));
        this.closeButton.setRolloverIcon(rolloverIcon);
        this.closeButton.setText(null);
        this.closeButton.setMargin(new Insets(0, 0, 0, 0));
        this.closeButton.setBorder(null);
        this.closeButton.setBorderPainted(false);
        this.closeButton.setFocusPainted(false);
        this.closeButton.setContentAreaFilled(false);

        this.closeButton.setActionCommand("doClose");
        this.closeButton.addActionListener(this);

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
