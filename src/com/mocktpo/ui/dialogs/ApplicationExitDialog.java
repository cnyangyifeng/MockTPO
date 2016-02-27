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

public class ApplicationExitDialog extends JDialog implements ActionListener {

    public static final int DIALOG_WIDTH = 440;
    public static final int DIALOG_HEIGHT = 420;

    public static final int TITLE_WIDTH = 200;
    public static final int TITLE_HEIGHT = 40;

    public static final int DESCRIPTION_PANE_WIDTH = 418;
    public static final int DESCRIPTION_PANE_HEIGHT = 260;

    public static final int RETURN_BUTTON_WIDTH = 74;
    public static final int RETURN_BUTTON_HEIGHT = 34;

    public static final int EXIT_BUTTON_WIDTH = 74;
    public static final int EXIT_BUTTON_HEIGHT = 34;

    /**************************************************
     * Properties
     **************************************************/

    protected DialogBodyPanel bodyPanel;
    protected JLabel titleLabel;
    protected JEditorPane descriptionPane;
    protected MButton returnButton;
    protected MButton exitButton;

    public ApplicationExitDialog(Frame owner, String title, boolean modal) {
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
        this.setReturnButton();
        this.setExitButton();

        this.bodyPanel.add(this.titleLabel);
        this.bodyPanel.add(this.descriptionPane);
        this.bodyPanel.add(this.returnButton);
        this.bodyPanel.add(this.exitButton);
    }

    protected void setTitleLabel() {
        this.titleLabel = new JLabel("Application Exit", JLabel.CENTER);
        this.titleLabel.setForeground(Color.WHITE);
        this.titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        int x = (this.bodyPanel.getWidth() - TITLE_WIDTH) / 2;
        this.titleLabel.setBounds(x, LayoutConstants.MARGIN, TITLE_WIDTH, TITLE_HEIGHT);
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
        style.addRule(".desc { background-color: #ffffff; font-family: Roboto; font-size: 12px; color: #333333; padding: 30px; }");
        this.descriptionPane.setEditorKit(kit);
        this.descriptionPane.setText("<div class='desc'>Are you sure you wish to exit?<br /><br />Click on <b>Return</b> to continue with us. Click on <b>Exit</b> if you are sure you want to exit this application.</div>");
    }

    protected void setReturnButton() {
        this.returnButton = new MButton();

        int x = this.bodyPanel.getWidth() / 2 - RETURN_BUTTON_WIDTH - LayoutConstants.MARGIN * 2;
        int y = this.descriptionPane.getY() + DESCRIPTION_PANE_HEIGHT + LayoutConstants.MARGIN * 2;
        this.returnButton.setBounds(x, y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "return.png"));
        this.returnButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "return_hi.png"));
        this.returnButton.setRolloverIcon(rolloverIcon);
        this.returnButton.setText(null);
        this.returnButton.setMargin(new Insets(0, 0, 0, 0));
        this.returnButton.setBorder(null);
        this.returnButton.setBorderPainted(false);
        this.returnButton.setFocusPainted(false);
        this.returnButton.setContentAreaFilled(false);

        this.returnButton.setActionCommand("doReturn");
        this.returnButton.addActionListener(this);
    }

    protected void setExitButton() {
        this.exitButton = new MButton();

        int x = this.bodyPanel.getWidth() / 2 + LayoutConstants.MARGIN * 2;
        int y = this.descriptionPane.getY() + DESCRIPTION_PANE_HEIGHT + LayoutConstants.MARGIN * 2;
        this.exitButton.setBounds(x, y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit.png"));
        this.exitButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit_hi.png"));
        this.exitButton.setRolloverIcon(rolloverIcon);
        this.exitButton.setText(null);
        this.exitButton.setMargin(new Insets(0, 0, 0, 0));
        this.exitButton.setBorder(null);
        this.exitButton.setBorderPainted(false);
        this.exitButton.setFocusPainted(false);
        this.exitButton.setContentAreaFilled(false);

        this.exitButton.setActionCommand("doExit");
        this.exitButton.addActionListener(this);
    }

    /**************************************************
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        if ("doExit".equals(e.getActionCommand())) {
            System.exit(0);
        } else if ("doReturn".equals(e.getActionCommand())) {
            this.dispose();
        }
    }
}
