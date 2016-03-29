package com.mocktpo.ui.dialogs;

import com.mocktpo.ui.widgets.DialogBodyPanel;
import com.mocktpo.ui.widgets.ImageButton;
import com.mocktpo.ui.widgets.StyledLabelPane;
import com.mocktpo.util.FontsConstants;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationExitDialog extends JDialog implements ActionListener {

    /* Constants */

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
     * Components
     **************************************************/

    protected DialogBodyPanel bodyPanel;
    protected JLabel titleLabel;
    protected StyledLabelPane descriptionPane;
    protected ImageButton returnButton;
    protected ImageButton exitButton;

    /**************************************************
     * Constructors
     **************************************************/

    public ApplicationExitDialog(Frame owner, String title, boolean modal) {
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
        /* Add to the parent component */
        this.getContentPane().add(this.bodyPanel);
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
        this.titleLabel.setFont(new Font(FontsConstants.SYSTEM_FONT, Font.BOLD, 16));
        int x = (this.bodyPanel.getWidth() - TITLE_WIDTH) / 2;
        this.titleLabel.setBounds(x, LayoutConstants.MARGIN, TITLE_WIDTH, TITLE_HEIGHT);
    }

    protected void setDescriptionPane() {
        /* Initialize component */
        int x = (this.bodyPanel.getWidth() - DESCRIPTION_PANE_WIDTH) / 2;
        int y = this.titleLabel.getY() + TITLE_HEIGHT + LayoutConstants.MARGIN * 2;
        String css = ".desc { background-color: #ffffff; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 12px; color: #333333; padding: 30px; }";
        String html = "<div class='desc'>Are you sure you wish to exit?<br /><br />Click on <b>Return</b> to continue with us. Click on <b>Exit</b> if you are sure you want to exit this application.</div>";
        this.descriptionPane = new StyledLabelPane(x, y, DESCRIPTION_PANE_WIDTH, DESCRIPTION_PANE_HEIGHT, css, html);
    }

    protected void setReturnButton() {
        /* Initialize component */
        int x = this.bodyPanel.getWidth() / 2 - RETURN_BUTTON_WIDTH - LayoutConstants.MARGIN * 2;
        int y = this.descriptionPane.getY() + DESCRIPTION_PANE_HEIGHT + LayoutConstants.MARGIN * 2;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "return.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "return_hi.png"));
        this.returnButton = new ImageButton(x, y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.returnButton.setActionCommand("doReturn");
        this.returnButton.addActionListener(this);
    }

    protected void setExitButton() {
        /* Initialize component */
        int x = this.bodyPanel.getWidth() / 2 + LayoutConstants.MARGIN * 2;
        int y = this.descriptionPane.getY() + DESCRIPTION_PANE_HEIGHT + LayoutConstants.MARGIN * 2;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "exit_hi.png"));
        this.exitButton = new ImageButton(x, y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
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
