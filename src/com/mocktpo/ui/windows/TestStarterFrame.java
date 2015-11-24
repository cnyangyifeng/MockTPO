package com.mocktpo.ui.windows;

import com.mocktpo.ui.dialogs.PauseDialog;
import com.mocktpo.ui.tests.misc.CopyrightPanel;
import com.mocktpo.ui.tests.misc.GeneralTestInfoPanel;
import com.mocktpo.ui.widgets.MButton;
import com.mocktpo.util.GlobalConstants;
import com.mocktpo.util.LayoutConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestStarterFrame extends TestFrame implements ActionListener {

    // Constants

    public static final int CONTINUE_BUTTON_WIDTH = 74;
    public static final int CONTINUE_BUTTON_HEIGHT = 34;

    // Components

    private MButton continueButton;

    private CopyrightPanel copyrightPanel;
    private GeneralTestInfoPanel generalTestInfoPanel;

    public TestStarterFrame(GraphicsConfiguration gc, MainFrame mainFrame) {
        super(gc, mainFrame);
    }

    @Override
    protected void configData() {
    }

    @Override
    protected void customizeHeaderPanel() {
        this.setControlButtons();
    }

    @Override
    protected void customizeBodyPanel() {
        // this.copyrightPanel = new CopyrightPanel(this.bodyBounds);
        // this.bodyPanel = this.copyrightPanel;
        this.generalTestInfoPanel = new GeneralTestInfoPanel(this.bodyBounds);
        this.bodyPanel = this.generalTestInfoPanel;
    }

    /**************************************************
     * Private methods
     **************************************************/

    private void setControlButtons() {
        this.setContinueButton();
    }

    private void setContinueButton() {
        this.continueButton = new MButton();

        int x = this.headerPanel.getWidth() - CONTINUE_BUTTON_WIDTH - LayoutConstants.MARGIN * 2;
        int y = LayoutConstants.MARGIN * 3;
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

        this.headerPanel.add(this.continueButton);
    }

    /**************************************************
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        switch (ac) {
            case "doPauseTest":
                logger.info("'Pause Test' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        PauseDialog pause = new PauseDialog(TestStarterFrame.this, "", true);
                        pause.setVisible(true);
                    }
                });
                break;
            case "doContinue":
                logger.info("'Continue' button pressed.");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // TODO
                    }
                });
                break;
            default:
                break;
        }
    }
}
