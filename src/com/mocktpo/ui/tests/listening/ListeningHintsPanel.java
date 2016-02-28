package com.mocktpo.ui.tests.listening;

import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.ui.widgets.StyledLabelPane;
import com.mocktpo.util.LayoutConstants;

import java.awt.*;

public class ListeningHintsPanel extends BodyPanel {

    /* Constants */

    public static final int DESCRIPTION_PANE_WIDTH = 500;
    public static final int DESCRIPTION_PANE_HEIGHT = 380;

    /* Components */

    protected StyledLabelPane descriptionPane;

    /* Variables */

    private String hints;

    /**************************************************
     * Constructors
     **************************************************/

    public ListeningHintsPanel(Rectangle bounds, String hints) {
        super(bounds);
        this.hints = hints;
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
        int y = LayoutConstants.MARGIN * 5;
        String css = ".hints { color: #333333; font-family: Roboto; font-size: 16px; margin-top: 80px; padding: 30px; text-align: center; }";
        String html = "<div class='hints'>" + this.hints + "</div>";
        this.descriptionPane = new StyledLabelPane(x, y, DESCRIPTION_PANE_WIDTH, DESCRIPTION_PANE_HEIGHT, css, html);
        /* Set background */
        this.descriptionPane.setBackground(new Color(255, 252, 239));
        /* Add to the parent component */
        this.add(this.descriptionPane);
    }
}
