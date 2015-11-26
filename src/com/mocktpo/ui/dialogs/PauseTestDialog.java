package com.mocktpo.ui.dialogs;

import com.mocktpo.ui.widgets.DialogBodyPanel;
import com.mocktpo.ui.widgets.MButton;
import com.mocktpo.ui.windows.MainFrame;
import com.mocktpo.ui.windows.TestFrame;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseTestDialog extends JDialog implements ActionListener {


    public static final int DIALOG_WIDTH = 440;
    public static final int DIALOG_HEIGHT = 420;

    public static final int TITLE_WIDTH = 200;
    public static final int TITLE_HEIGHT = 40;

    public static final int DESCRIPTION_PANE_WIDTH = 418;
    public static final int DESCRIPTION_PANE_HEIGHT = 260;

    public static final int CONTINUE_BUTTON_WIDTH = 74;
    public static final int CONTINUE_BUTTON_HEIGHT = 34;

    public static final int RETURN_BUTTON_WIDTH = 74;
    public static final int RETURN_BUTTON_HEIGHT = 34;

    /**************************************************
     * Properties
     **************************************************/

    private DialogBodyPanel bodyPanel;
    private JLabel titleLabel;
    private JEditorPane descriptionPane;
    private MButton continueButton;
    private MButton returnButton;

    public PauseTestDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        initComponents();
    }

    protected void initComponents() {
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
        this.setContinueButton();

        this.bodyPanel.add(this.titleLabel);
        this.bodyPanel.add(this.descriptionPane);
        this.bodyPanel.add(this.continueButton);
        this.bodyPanel.add(this.returnButton);
    }

    protected void setTitleLabel() {
        this.titleLabel = new JLabel("Pause Test", JLabel.CENTER);
        this.titleLabel.setForeground(Color.WHITE);
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
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
        style.addRule(".desc { background-color: #ffffff; font-family: Arial; font-size: 12px; color: #333333; padding: 30px; }");
        this.descriptionPane.setEditorKit(kit);
        this.descriptionPane.setText("<div class='desc'>You may now pause this test and resume work at any later time; however the score you obtain may not be an accurate indication of a score you would earn in a timed test. When you resume the test, you will return to the question from which you left.<br /><br />Are you sure you wish to pause the test? Click on <b>Return</b> to continue working. Click on <b>Continue</b> to pause the test.</div>");
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

    protected void setContinueButton() {
        this.continueButton = new MButton();

        int x = this.bodyPanel.getWidth() / 2 + LayoutConstants.MARGIN * 2;
        int y = this.descriptionPane.getY() + DESCRIPTION_PANE_HEIGHT + LayoutConstants.MARGIN * 2;
        this.continueButton.setBounds(x, y, CONTINUE_BUTTON_WIDTH, CONTINUE_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "continue.png"));
        this.continueButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "continue_hi.png"));
        this.continueButton.setRolloverIcon(rolloverIcon);
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
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        if ("doContinue".equals(e.getActionCommand())) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice device = ge.getDefaultScreenDevice();
                    TestFrame testFrame = (TestFrame) PauseTestDialog.this.getOwner();
                    MainFrame mainFrame = testFrame.getMainFrame();

                    PauseTestDialog.this.dispose();
                    testFrame.dispose();

                    if (mainFrame == null) {
                        mainFrame = new MainFrame(device.getDefaultConfiguration());
                    }
                    device.setFullScreenWindow(mainFrame);
                    mainFrame.setVisible(true);
                }
            });
        } else if ("doReturn".equals(e.getActionCommand())) {
            this.dispose();
        }
    }
}
