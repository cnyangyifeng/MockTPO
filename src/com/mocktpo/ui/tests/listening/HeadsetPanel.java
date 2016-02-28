package com.mocktpo.ui.tests.listening;

import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.ui.widgets.StyledLabelPane;
import com.mocktpo.util.GlobalConstants;

import java.awt.*;

public class HeadsetPanel extends BodyPanel {

    /* Constants */

    public static final int DESCRIPTION_PANE_WIDTH = 600;
    public static final int DESCRIPTION_PANE_HEIGHT = 480;

    /* Variables */

    protected StyledLabelPane descriptionPane;

    /**************************************************
     * Constructors
     **************************************************/

    public HeadsetPanel(Rectangle bounds) {
        super(bounds);
        this.initComponents();
    }

    /**************************************************
     * Components Initialization
     **************************************************/

    private void initComponents() {
        /* Set layout */
        this.setLayout(null);
        /* Set components */
        this.setDescriptionPane();
    }

    protected void setDescriptionPane() {
        /* Initialize component */
        int x = (this.getWidth() - DESCRIPTION_PANE_WIDTH) / 2;
        int y = (this.getHeight() - DESCRIPTION_PANE_HEIGHT) / 2;
        String css = ".hs-header { color: #333333; font-family: Roboto; font-size: 12px; margin-bottom: 20px; text-align: center; } .hs-body { margin-bottom: 20px; text-align: center; } .hs-footer { color: #333333; font-family: Roboto; font-size: 12px; text-align: center; }";
        String html = "<div class='hs-header'>Now put on your headset.</div>";
        String imgUrl = this.getClass().getResource(GlobalConstants.IMAGES_DIR + "headset.png").toString();
        html += "<div class='hs-body'><img src='" + imgUrl + "' /></div>";
        html += "<div class='hs-footer'>Click on <b>Continue</b> to go on.</div>";
        this.descriptionPane = new StyledLabelPane(x, y, DESCRIPTION_PANE_WIDTH, DESCRIPTION_PANE_HEIGHT, css, html);
        /* Add to the parent component */
        this.add(this.descriptionPane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        Color bg = new Color(238, 213, 204);
        g2d.setPaint(bg);
        g2d.fillRect(0, 0, width, height);
    }
}
