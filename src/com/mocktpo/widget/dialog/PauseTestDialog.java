package com.mocktpo.widget.dialog;

import com.mocktpo.util.FontsConstants;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;
import com.mocktpo.widget.button.ImageButton;
import com.mocktpo.widget.panel.DialogBodyPanel;
import com.mocktpo.widget.panel.StyledLabelPane;
import com.mocktpo.window.AppWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseTestDialog extends MyDialog implements ActionListener {

    /* Constants */

    private static final int DIALOG_WIDTH = 440;
    private static final int DIALOG_HEIGHT = 420;

    private static final int TITLE_WIDTH = 200;
    private static final int TITLE_HEIGHT = 40;

    private static final int DESCRIPTION_PANE_WIDTH = 418;
    private static final int DESCRIPTION_PANE_HEIGHT = 260;

    private static final int CONTINUE_BUTTON_WIDTH = 74;
    private static final int CONTINUE_BUTTON_HEIGHT = 34;

    private static final int RETURN_BUTTON_WIDTH = 74;
    private static final int RETURN_BUTTON_HEIGHT = 34;

    /**************************************************
     * Components
     **************************************************/

    private DialogBodyPanel bodyPanel;
    private JLabel titleLabel;
    private StyledLabelPane descriptionPane;
    private ImageButton continueButton;
    private ImageButton returnButton;

    /**************************************************
     * Constructors
     **************************************************/

    public PauseTestDialog(AppWindow owner, String title, boolean modal) {
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

    private void globalSettings() {
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

    private void setBodyPanel() {
        this.bodyPanel = new DialogBodyPanel();
        this.bodyPanel.setBounds(0, 0, this.getWidth(), this.getHeight());

        this.bodyPanel.setLayout(null);

        this.setTitleLabel();
        this.setDescriptionPane();
        this.setReturnButton();
        this.setContinueButton();

        this.bodyPanel.add(this.titleLabel);
        this.bodyPanel.add(this.descriptionPane);
        this.bodyPanel.add(this.continueButton);
        this.bodyPanel.add(this.returnButton);
    }

    private void setTitleLabel() {
        this.titleLabel = new JLabel("Pause Test", JLabel.CENTER);
        this.titleLabel.setForeground(Color.WHITE);
        this.titleLabel.setFont(new Font(FontsConstants.SYSTEM_FONT, Font.BOLD, 16));
        int x = (this.bodyPanel.getWidth() - TITLE_WIDTH) / 2;
        this.titleLabel.setBounds(x, LayoutConstants.MARGIN, TITLE_WIDTH, TITLE_HEIGHT);
    }

    private void setDescriptionPane() {
        /* Initialize component */
        int x = (this.bodyPanel.getWidth() - DESCRIPTION_PANE_WIDTH) / 2;
        int y = this.titleLabel.getY() + TITLE_HEIGHT + LayoutConstants.MARGIN * 2;
        String css = ".desc { background-color: #ffffff; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 12px; color: #333333; padding: 20px; }";
        String html = "<div class='desc'>You may now pause this test and resume work at any later time; however the score you obtain may not be an accurate indication of a score you would earn in a timed test. When you resume the test, you will return to the question from which you left.<br /><br />Are you sure you wish to pause the test? Click on <b>Return</b> to continue working. Click on <b>Continue</b> to pause the test.</div>";
        this.descriptionPane = new StyledLabelPane(x, y, DESCRIPTION_PANE_WIDTH, DESCRIPTION_PANE_HEIGHT, css, html);
    }

    private void setReturnButton() {
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

    private void setContinueButton() {
        /* Initialize component */
        int x = this.bodyPanel.getWidth() / 2 + LayoutConstants.MARGIN * 2;
        int y = this.descriptionPane.getY() + DESCRIPTION_PANE_HEIGHT + LayoutConstants.MARGIN * 2;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "continue.png"));
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "continue_hi.png"));
        this.continueButton = new ImageButton(x, y, CONTINUE_BUTTON_WIDTH, CONTINUE_BUTTON_HEIGHT, icon, rolloverIcon);
        /* Set actions */
        this.continueButton.setActionCommand("doContinue");
        this.continueButton.addActionListener(this);
    }

    /**************************************************
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        if ("doContinue".equals(e.getActionCommand())) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    PauseTestDialog.this.dispose();
                    AppWindow win = PauseTestDialog.this.getAppWindow();
                    win.setContentPane(win.getTestsHomeView());
                }
            });
        } else if ("doReturn".equals(e.getActionCommand())) {
            this.dispose();
        }
    }
}
