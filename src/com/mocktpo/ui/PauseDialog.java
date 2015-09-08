package com.mocktpo.ui;

import com.mocktpo.util.Constants;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseDialog extends JDialog implements ActionListener {

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

        int x = ((int) screenSize.getWidth() - Constants.PAUSE_DIALOG_WIDTH) / 2;
        int y = ((int) screenSize.getHeight() - Constants.PAUSE_DIALOG_HEIGHT) / 2;
        this.setBounds(x, y, Constants.PAUSE_DIALOG_WIDTH, Constants.PAUSE_DIALOG_HEIGHT);

        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setTitle("");
    }

    private void setBodyPanel() {
        this.bodyPanel = new PauseBodyPanel();
        this.bodyPanel.setBounds(0, 0, this.getWidth(), this.getHeight());

        this.bodyPanel.setLayout(null);

        this.setPauseLabel();
        this.setPauseDescriptionPane();
        this.setPauseGoButton();
        this.setPauseCancelButton();

        this.bodyPanel.add(this.pauseLabel);
        this.bodyPanel.add(this.pauseDescriptionPane);
        this.bodyPanel.add(this.pauseGoButton);
        this.bodyPanel.add(this.pauseCancelButton);
    }

    private void setPauseLabel() {
        this.pauseLabel = new JLabel("Pause", JLabel.CENTER);
        this.pauseLabel.setForeground(Color.WHITE);
        this.pauseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        int x = (this.bodyPanel.getWidth() - Constants.PAUSE_LABEL_WIDTH) / 2;
        this.pauseLabel.setBounds(x, Constants.MARGIN, Constants.PAUSE_LABEL_WIDTH, Constants.PAUSE_LABEL_HEIGHT);
    }

    private void setPauseDescriptionPane() {
        this.pauseDescriptionPane = new JEditorPane();

        int x = (this.bodyPanel.getWidth() - Constants.PAUSE_DESCRIPTION_WIDTH) / 2;
        int y = this.pauseLabel.getY() + Constants.PAUSE_LABEL_HEIGHT + Constants.MARGIN * 2;
        this.pauseDescriptionPane.setBounds(x, y, Constants.PAUSE_DESCRIPTION_WIDTH, Constants.PAUSE_DESCRIPTION_HEIGHT);

        this.pauseDescriptionPane.setEditable(false);
        this.pauseDescriptionPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".desc { background-color: #ffffff; font-family: Arial; font-size: 12px; font-weight: bold; color: #333333; padding: 30px; }");
        this.pauseDescriptionPane.setEditorKit(kit);
        this.pauseDescriptionPane.setText("<div class='desc'>You may now pause this test and resume work at any later time; however the score you obtain may not be an accurate indication of a score you would earn in a timed test. When you resume the test, you will return to the question from which you left. Are you sure you wish to pause the test? Click on Return to continue working. Click on Continue to pause the test.</div>");
    }

    private void setPauseGoButton() {
        this.pauseGoButton = new JButton();

        int x = this.bodyPanel.getWidth() / 2 - Constants.PAUSE_GO_BUTTON_WIDTH - Constants.MARGIN * 2;
        int y = this.pauseDescriptionPane.getY() + Constants.PAUSE_DESCRIPTION_HEIGHT + Constants.MARGIN * 2;
        this.pauseGoButton.setBounds(x, y, Constants.PAUSE_GO_BUTTON_WIDTH, Constants.PAUSE_GO_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("pause_test.png"));
        this.pauseGoButton.setIcon(icon);
        this.pauseGoButton.setText(null);
        this.pauseGoButton.setMargin(new Insets(0, 0, 0, 0));
        this.pauseGoButton.setBorder(null);
        this.pauseGoButton.setBorderPainted(false);
        this.pauseGoButton.setFocusPainted(false);
        this.pauseGoButton.setContentAreaFilled(false);

        this.pauseGoButton.setActionCommand("doPauseGo");
        this.pauseGoButton.addActionListener(this);
    }

    private void setPauseCancelButton() {
        this.pauseCancelButton = new JButton();

        int x = this.bodyPanel.getWidth() / 2 + Constants.MARGIN * 2;
        int y = this.pauseDescriptionPane.getY() + Constants.PAUSE_DESCRIPTION_HEIGHT + Constants.MARGIN * 2;
        this.pauseCancelButton.setBounds(x, y, Constants.PAUSE_CANCEL_BUTTON_WIDTH, Constants.PAUSE_CANCEL_BUTTON_HEIGHT);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("pause_test.png"));
        this.pauseCancelButton.setIcon(icon);
        this.pauseCancelButton.setText(null);
        this.pauseCancelButton.setMargin(new Insets(0, 0, 0, 0));
        this.pauseCancelButton.setBorder(null);
        this.pauseCancelButton.setBorderPainted(false);
        this.pauseCancelButton.setFocusPainted(false);
        this.pauseCancelButton.setContentAreaFilled(false);

        this.pauseCancelButton.setActionCommand("doPauseCancel");
        this.pauseCancelButton.addActionListener(this);
    }

    /**************************************************
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("doPauseGo")) {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice device = ge.getDefaultScreenDevice();
            TestFrame testFrame = (TestFrame) this.getOwner();
            MainFrame mainFrame = testFrame.getMainFrame();

            this.dispose();
            testFrame.dispose();

            if (mainFrame == null) {
                mainFrame = new MainFrame(device.getDefaultConfiguration());
            }
            device.setFullScreenWindow(mainFrame);
            mainFrame.setVisible(true);
        } else if (e.getActionCommand().equals("doPauseCancel")) {
            this.dispose();
        }
    }

    /**************************************************
     * Properties
     **************************************************/

    private PauseBodyPanel bodyPanel;
    private JLabel pauseLabel;
    private JEditorPane pauseDescriptionPane;
    private JButton pauseGoButton;
    private JButton pauseCancelButton;
}
