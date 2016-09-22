package com.mocktpo.view.tests.misc;

import com.mocktpo.view.widgets.BodyPanel;
import com.mocktpo.view.widgets.StyledLabelPane;
import com.mocktpo.util.FontsConstants;
import com.mocktpo.util.GlobalConstants;

import java.awt.*;

public class CopyrightPanel extends BodyPanel {

    /* Constants */

    public static final int DESCRIPTION_PANE_WIDTH = 800;
    public static final int DESCRIPTION_PANE_HEIGHT = 400;

    /* Components */

    protected StyledLabelPane descriptionPane;

    /**************************************************
     * Constructors
     **************************************************/

    public CopyrightPanel(Rectangle bounds) {
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
        String css = ".cp-img { margin-bottom: 40px; text-align: center; } .cp-img img { height: 90px; width: 160px; } .cp { color: #333333; font-family: " + FontsConstants.SYSTEM_FONT + "; font-size: 12px; text-align: center; }";
        String imgUrl = this.getClass().getResource(GlobalConstants.IMAGES_DIR + "ets_toefl.png").toString();
        String html = "<div class='cp-img'><img src='" + imgUrl + "' /></div>";
        html += "<div class='cp'>EDUCATIONAL TESTING SERVICE, ETS, the ETS logo, TOEFL and TOEFL iBT are registered trademarks of<br />Educational Testing Service (ETS) in the United States and other countries.<br /><br />Click on <b>Continue</b> to go on.</div>";
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
