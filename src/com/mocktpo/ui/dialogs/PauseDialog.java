package com.mocktpo.ui.dialogs;

import com.mocktpo.ui.widgets.MButton;
import com.mocktpo.ui.widgets.PauseDialogBodyPanel;
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

public class PauseDialog extends JDialog implements ActionListener {

    public static final int PAUSE_DIALOG_WIDTH = 440;
    public static final int PAUSE_DIALOG_HEIGHT = 420;

    public static final int PAUSE_LABEL_WIDTH = 200;
    public static final int PAUSE_LABEL_HEIGHT = 40;

    public static final int PAUSE_DESCRIPTION_PANE_WIDTH = 418;
    public static final int PAUSE_DESCRIPTION_PANE_HEIGHT = 260;

    public static final int PAUSE_GO_BUTTON_WIDTH = 74;
    public static final int PAUSE_GO_BUTTON_HEIGHT = 34;

    public static final int PAUSE_CANCEL_BUTTON_WIDTH = 74;
    public static final int PAUSE_CANCEL_BUTTON_HEIGHT = 34;

    /**************************************************
     * Properties
     **************************************************/

    private PauseDialogBodyPanel bodyPanel;
    private JLabel pauseLabel;
    private JEditorPane pauseDescriptionPane;
    private MButton pauseGoButton;
    private MButton pauseCancelButton;

    public PauseDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        initComponents();
    }

    private void initComponents() {
        this.globalSettings();

        this.setLayout(null);

        this.setBodyPanel();

        this.getContentPane().add(this.bodyPanel);
    }

    private void globalSettings() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        int x = ((int) screenSize.getWidth() - PAUSE_DIALOG_WIDTH) / 2;
        int y = ((int) screenSize.getHeight() - PAUSE_DIALOG_HEIGHT) / 2;
        this.setBounds(x, y, PAUSE_DIALOG_WIDTH, PAUSE_DIALOG_HEIGHT);

        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setTitle("");
    }

    private void setBodyPanel() {
        this.bodyPanel = new PauseDialogBodyPanel();
        this.bodyPanel.setBounds(0, 0, this.getWidth(), this.getHeight());

        this.bodyPanel.setLayout(null);

        this.setPauseLabel();
        this.setPauseDescriptionPane();
        this.setPauseCancelButton();
        this.setPauseGoButton();

        this.bodyPanel.add(this.pauseLabel);
        this.bodyPanel.add(this.pauseDescriptionPane);
        this.bodyPanel.add(this.pauseCancelButton);
        this.bodyPanel.add(this.pauseGoButton);
    }

    private void setPauseLabel() {
        this.pauseLabel = new JLabel("Pause", JLabel.CENTER);
        this.pauseLabel.setForeground(Color.WHITE);
        this.pauseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        int x = (this.bodyPanel.getWidth() - PAUSE_LABEL_WIDTH) / 2;
        this.pauseLabel.setBounds(x, LayoutConstants.MARGIN, PAUSE_LABEL_WIDTH, PAUSE_LABEL_HEIGHT);
    }

    private void setPauseDescriptionPane() {
        this.pauseDescriptionPane = new JEditorPane();

        int x = (this.bodyPanel.getWidth() - PAUSE_DESCRIPTION_PANE_WIDTH) / 2;
        int y = this.pauseLabel.getY() + PAUSE_LABEL_HEIGHT + LayoutConstants.MARGIN * 2;
        this.pauseDescriptionPane.setBounds(x, y, PAUSE_DESCRIPTION_PANE_WIDTH, PAUSE_DESCRIPTION_PANE_HEIGHT);

        this.pauseDescriptionPane.setEditable(false);
        this.pauseDescriptionPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".desc { background-color: #ffffff; font-family: Arial; font-size: 12px; font-weight: bold; color: #333333; padding: 30px; }");
        this.pauseDescriptionPane.setEditorKit(kit);
        this.pauseDescriptionPane.setText("<div class='desc'>You may now pause this test and resume work at any later time; however the score you obtain may not be an accurate indication of a score you would earn in a timed test. When you resume the test, you will return to the question from which you left. Are you sure you wish to pause the test? Click on Return to continue working. Click on Continue to pause the test.</div>");
    }

    private void setPauseCancelButton() {
        this.pauseCancelButton = new MButton();

        int x = this.bodyPanel.getWidth() / 2 - PAUSE_CANCEL_BUTTON_WIDTH - LayoutConstants.MARGIN * 2;
        int y = this.pauseDescriptionPane.getY() + PAUSE_DESCRIPTION_PANE_HEIGHT + LayoutConstants.MARGIN * 2;
        this.pauseCancelButton.setBounds(x, y, PAUSE_CANCEL_BUTTON_WIDTH, PAUSE_CANCEL_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "return.png"));
        this.pauseCancelButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "return_hi.png"));
        this.pauseCancelButton.setRolloverIcon(rolloverIcon);
        this.pauseCancelButton.setText(null);
        this.pauseCancelButton.setMargin(new Insets(0, 0, 0, 0));
        this.pauseCancelButton.setBorder(null);
        this.pauseCancelButton.setBorderPainted(false);
        this.pauseCancelButton.setFocusPainted(false);
        this.pauseCancelButton.setContentAreaFilled(false);

        this.pauseCancelButton.setActionCommand("doPauseCancel");
        this.pauseCancelButton.addActionListener(this);
    }

    private void setPauseGoButton() {
        this.pauseGoButton = new MButton();

        int x = this.bodyPanel.getWidth() / 2 + LayoutConstants.MARGIN * 2;
        int y = this.pauseDescriptionPane.getY() + PAUSE_DESCRIPTION_PANE_HEIGHT + LayoutConstants.MARGIN * 2;
        this.pauseGoButton.setBounds(x, y, PAUSE_GO_BUTTON_WIDTH, PAUSE_GO_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "pause_continue.png"));
        this.pauseGoButton.setIcon(icon);
        ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(GlobalConstants.IMAGES_DIR + "pause_continue_hi.png"));
        this.pauseGoButton.setRolloverIcon(rolloverIcon);
        this.pauseGoButton.setText(null);
        this.pauseGoButton.setMargin(new Insets(0, 0, 0, 0));
        this.pauseGoButton.setBorder(null);
        this.pauseGoButton.setBorderPainted(false);
        this.pauseGoButton.setFocusPainted(false);
        this.pauseGoButton.setContentAreaFilled(false);

        this.pauseGoButton.setActionCommand("doPauseGo");
        this.pauseGoButton.addActionListener(this);
    }

    /**************************************************
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        if ("doPauseGo".equals(e.getActionCommand())) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice device = ge.getDefaultScreenDevice();
                    TestFrame testFrame = (TestFrame) PauseDialog.this.getOwner();
                    MainFrame mainFrame = testFrame.getMainFrame();

                    PauseDialog.this.dispose();
                    testFrame.didPauseTest();
                    testFrame.dispose();

                    if (mainFrame == null) {
                        mainFrame = new MainFrame(device.getDefaultConfiguration());
                    }
                    device.setFullScreenWindow(mainFrame);
                    mainFrame.setVisible(true);
                }
            });
        } else if ("doPauseCancel".equals(e.getActionCommand())) {
            this.dispose();
        }
    }
}
