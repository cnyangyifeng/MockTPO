package com.mocktpo.ui.widgets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class BodyPanel extends JPanel {

    /* Logger */

    protected static final Logger logger = LogManager.getLogger();

    /**************************************************
     * Control Buttons Status - Available
     **************************************************/

    private boolean sectionExitButtonAvailable;
    private boolean questionNumberPaneAvailable;
    private boolean continueOvalButtonAvailable;
    private boolean nextButtonAvailable;
    private boolean backButtonAvailable;
    private boolean okButtonAvailable;
    private boolean helpButtonAvailable;
    private boolean reviewButtonAvailable;
    private boolean volumeButtonAvailable;
    private boolean viewTextOrQuestionButtonAvailable;
    private boolean continueButtonAvailable;
    private boolean goToQuestionButtonAvailable;
    private boolean returnButtonAvailable;
    private boolean timerLabelAvailable;
    private boolean hideOrShowTimerButtonAvailable;

    /**************************************************
     * Control Buttons Status - Enabled
     **************************************************/

    private boolean nextButtonEnabled;
    private boolean backButtonEnabled;
    private boolean okButtonEnabled;
    private boolean helpButtonEnabled;
    private boolean reviewButtonEnabled;
    private boolean volumeButtonEnabled;

    /**************************************************
     * Constructors
     **************************************************/

    public BodyPanel(Rectangle bounds) {
        super();
        this.setBounds(bounds);
        this.initComponents();
        this.initButtonStatus();
    }

    /**************************************************
     * Components Initialization
     **************************************************/

    private void initComponents() {
        /* Set layout */
        this.setLayout(null);
    }

    protected void initButtonStatus() {
        this.setSectionExitButtonAvailable(false);
        this.setQuestionNumberPaneAvailable(false);
        this.setContinueOvalButtonAvailable(false);
        this.setNextButtonAvailable(false);
        this.setBackButtonAvailable(false);
        this.setOkButtonAvailable(false);
        this.setHelpButtonAvailable(false);
        this.setReviewButtonAvailable(false);
        this.setVolumeButtonAvailable(false);
        this.setViewTextOrQuestionButtonAvailable(false);
        this.setContinueButtonAvailable(true);
        this.setGoToQuestionButtonAvailable(false);
        this.setReturnButtonAvailable(false);
        this.setTimerLabelAvailable(false);
        this.setHideOrShowTimerButtonAvailable(false);
        this.setNextButtonEnabled(false);
        this.setBackButtonEnabled(false);
        this.setOkButtonEnabled(false);
        this.setHelpButtonEnabled(false);
        this.setReviewButtonEnabled(false);
        this.setVolumeButtonEnabled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        Color bg = new Color(255, 255, 255); // #ffffff
        g2d.setPaint(bg);
        g2d.fillRect(0, 0, width, height);
    }

    /**************************************************
     * Control Buttons Status - Available
     **************************************************/

    public boolean isSectionExitButtonAvailable() {
        return sectionExitButtonAvailable;
    }

    public void setSectionExitButtonAvailable(boolean sectionExitButtonAvailable) {
        this.sectionExitButtonAvailable = sectionExitButtonAvailable;
    }

    public boolean isQuestionNumberPaneAvailable() {
        return questionNumberPaneAvailable;
    }

    public void setQuestionNumberPaneAvailable(boolean questionNumberPaneAvailable) {
        this.questionNumberPaneAvailable = questionNumberPaneAvailable;
    }

    public boolean isContinueOvalButtonAvailable() {
        return continueOvalButtonAvailable;
    }

    public void setContinueOvalButtonAvailable(boolean continueOvalButtonAvailable) {
        this.continueOvalButtonAvailable = continueOvalButtonAvailable;
    }

    public boolean isNextButtonAvailable() {
        return nextButtonAvailable;
    }

    public void setNextButtonAvailable(boolean nextButtonAvailable) {
        this.nextButtonAvailable = nextButtonAvailable;
    }

    public boolean isBackButtonAvailable() {
        return backButtonAvailable;
    }

    public void setBackButtonAvailable(boolean backButtonAvailable) {
        this.backButtonAvailable = backButtonAvailable;
    }

    public boolean isOkButtonAvailable() {
        return okButtonAvailable;
    }

    public void setOkButtonAvailable(boolean okButtonAvailable) {
        this.okButtonAvailable = okButtonAvailable;
    }

    public boolean isHelpButtonAvailable() {
        return helpButtonAvailable;
    }

    public void setHelpButtonAvailable(boolean helpButtonAvailable) {
        this.helpButtonAvailable = helpButtonAvailable;
    }

    public boolean isReviewButtonAvailable() {
        return reviewButtonAvailable;
    }

    public void setReviewButtonAvailable(boolean reviewButtonAvailable) {
        this.reviewButtonAvailable = reviewButtonAvailable;
    }

    public boolean isVolumeButtonAvailable() {
        return volumeButtonAvailable;
    }

    public void setVolumeButtonAvailable(boolean volumeButtonAvailable) {
        this.volumeButtonAvailable = volumeButtonAvailable;
    }

    public boolean isViewTextOrQuestionButtonAvailable() {
        return viewTextOrQuestionButtonAvailable;
    }

    public void setViewTextOrQuestionButtonAvailable(boolean viewTextOrQuestionButtonAvailable) {
        this.viewTextOrQuestionButtonAvailable = viewTextOrQuestionButtonAvailable;
    }

    public boolean isContinueButtonAvailable() {
        return continueButtonAvailable;
    }

    public void setContinueButtonAvailable(boolean continueButtonAvailable) {
        this.continueButtonAvailable = continueButtonAvailable;
    }

    public boolean isGoToQuestionButtonAvailable() {
        return goToQuestionButtonAvailable;
    }

    public void setGoToQuestionButtonAvailable(boolean goToQuestionButtonAvailable) {
        this.goToQuestionButtonAvailable = goToQuestionButtonAvailable;
    }

    public boolean isReturnButtonAvailable() {
        return returnButtonAvailable;
    }

    public void setReturnButtonAvailable(boolean returnButtonAvailable) {
        this.returnButtonAvailable = returnButtonAvailable;
    }

    public boolean isTimerLabelAvailable() {
        return timerLabelAvailable;
    }

    public void setTimerLabelAvailable(boolean timerLabelAvailable) {
        this.timerLabelAvailable = timerLabelAvailable;
    }

    public boolean isHideOrShowTimerButtonAvailable() {
        return hideOrShowTimerButtonAvailable;
    }

    public void setHideOrShowTimerButtonAvailable(boolean hideOrShowTimerButtonAvailable) {
        this.hideOrShowTimerButtonAvailable = hideOrShowTimerButtonAvailable;
    }

    /**************************************************
     * Control Buttons Status - Enabled
     **************************************************/

    public boolean isNextButtonEnabled() {
        return nextButtonEnabled;
    }

    public void setNextButtonEnabled(boolean nextButtonEnabled) {
        this.nextButtonEnabled = nextButtonEnabled;
    }

    public boolean isBackButtonEnabled() {
        return backButtonEnabled;
    }

    public void setBackButtonEnabled(boolean backButtonEnabled) {
        this.backButtonEnabled = backButtonEnabled;
    }

    public boolean isOkButtonEnabled() {
        return okButtonEnabled;
    }

    public void setOkButtonEnabled(boolean okButtonEnabled) {
        this.okButtonEnabled = okButtonEnabled;
    }

    public boolean isHelpButtonEnabled() {
        return helpButtonEnabled;
    }

    public void setHelpButtonEnabled(boolean helpButtonEnabled) {
        this.helpButtonEnabled = helpButtonEnabled;
    }

    public boolean isReviewButtonEnabled() {
        return reviewButtonEnabled;
    }

    public void setReviewButtonEnabled(boolean reviewButtonEnabled) {
        this.reviewButtonEnabled = reviewButtonEnabled;
    }

    public boolean isVolumeButtonEnabled() {
        return volumeButtonEnabled;
    }

    public void setVolumeButtonEnabled(boolean volumeButtonEnabled) {
        this.volumeButtonEnabled = volumeButtonEnabled;
    }
}
