package com.mocktpo.ui.tests.reading;

import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.ui.widgets.StyledLabelPane;

import java.awt.*;

public class ReadingDirectionsPanel extends BodyPanel {

    /* Components */

    protected StyledLabelPane descriptionPane;

    /**************************************************
     * Constructors
     **************************************************/

    public ReadingDirectionsPanel(Rectangle bounds) {
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

    @Override
    protected void initButtonStatus() {
        this.setSectionExitButtonAvailable(true);
        this.setNextButtonAvailable(true);
        this.setBackButtonAvailable(true);
        this.setHelpButtonAvailable(true);
        this.setReviewButtonAvailable(true);
        this.setContinueButtonAvailable(true);
        this.setHelpButtonEnabled(true);
    }

    protected void setDescriptionPane() {
        /* Initialize component */
        String css = ".rd-header { color: #3d4167; font-family: Cambria; font-size: 18px; font-weight: bold; text-align: center; margin-top: 60px; margin-bottom: 40px; } .rd { color: #333333; font-family: Roboto; font-size: 12px; margin-left: 24px; margin-right: 24px; margin-bottom: 12px; } .rd a { color: #3c4d82; }";
        String html = "<div class='rd-header'>Reading Section Directions</div>";
        html += "<div class='rd'>This section measures your ability to understand academic passages in English. You will read <b>3 passages</b>. In an actual test you will have <b>60 minutes (1 hour)</b> to read the passages and answer the questions.</div>";
        html += "<div class='rd'>Most questions are worth 1 point but the last question in each set is worth more than 1 point. The directions indicate how many points you may receive.</div>";
        html += "<div class='rd'>Some passages include a word or phrase that is <a href=''>underlined</a> in blue. Click on the word or phrase to see a definition or an explanation.</div>";
        html += "<div class='rd'>Within this section, you can go to the next question by clicking <b>Next</b>. You may skip questions and go back to them later. If you want to return to previous questions, click on <b>Back</b>. You can click on <b>Review</b> at any time and the review screen will show you which questions you have answered and which you have not answered. From this review screen, you may go directly to any question you have already seen in the Reading section.</div>";
        html += "<div class='rd'>During this practice test, you may click the <b>Pause</b> icon at any time. This will stop the test until you decide to continue. You may continue the test in a few minutes or at any time during the period that your test is activated.</div>";
        html += "<div class='rd'>You may now begin the Reading section. Again, in an actual test you will have <b>60 minutes (1 hour)</b> to read the 3 passages and answer the questions. NOTE: Also in an actual test some test takers may receive 4 passages; those test takers will have 80 minutes (1 hour and 20 minutes) to answer the questions.</div>";
        html += "<div class='rd'>Click on <b>Continue</b> to go on.</div>";
        this.descriptionPane = new StyledLabelPane(0, 0, this.getWidth(), this.getHeight(), css, html);
        /* Add to the parent component */
        this.add(this.descriptionPane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        Color bg = new Color(242, 232, 200); // #f2e8c8
        g2d.setPaint(bg);
        g2d.fillRect(0, 0, width, height);
    }
}
