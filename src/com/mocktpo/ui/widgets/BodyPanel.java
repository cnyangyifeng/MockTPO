package com.mocktpo.ui.widgets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class BodyPanel extends JPanel {

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

    public BodyPanel(Rectangle bounds) {
        super();
        this.setBounds(bounds);
        this.initComponents();
        this.initButtonStatus();
    }

    private void initComponents() {
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

    public boolean isQuestionNumberPaneAvailable() {
        return questionNumberPaneAvailable;
    }

    public boolean isContinueOvalButtonAvailable() {
        return continueOvalButtonAvailable;
    }

    public boolean isNextButtonAvailable() {
        return nextButtonAvailable;
    }

    public boolean isBackButtonAvailable() {
        return backButtonAvailable;
    }

    public boolean isOkButtonAvailable() {
        return okButtonAvailable;
    }

    public boolean isHelpButtonAvailable() {
        return helpButtonAvailable;
    }

    public boolean isReviewButtonAvailable() {
        return reviewButtonAvailable;
    }

    public boolean isVolumeButtonAvailable() {
        return volumeButtonAvailable;
    }

    public boolean isContinueButtonAvailable() {
        return continueButtonAvailable;
    }

    public boolean isGoToQuestionButtonAvailable() {
        return goToQuestionButtonAvailable;
    }

    public boolean isReturnButtonAvailable() {
        return returnButtonAvailable;
    }

    public boolean isTimerLabelAvailable() {
        return timerLabelAvailable;
    }

    public boolean isHideOrShowTimerButtonAvailable() {
        return hideOrShowTimerButtonAvailable;
    }

    public void setSectionExitButtonAvailable(boolean sectionExitButtonAvailable) {
        this.sectionExitButtonAvailable = sectionExitButtonAvailable;
    }

    public void setQuestionNumberPaneAvailable(boolean questionNumberPaneAvailable) {
        this.questionNumberPaneAvailable = questionNumberPaneAvailable;
    }

    public void setContinueOvalButtonAvailable(boolean continueOvalButtonAvailable) {
        this.continueOvalButtonAvailable = continueOvalButtonAvailable;
    }

    public void setNextButtonAvailable(boolean nextButtonAvailable) {
        this.nextButtonAvailable = nextButtonAvailable;
    }

    public void setBackButtonAvailable(boolean backButtonAvailable) {
        this.backButtonAvailable = backButtonAvailable;
    }

    public void setOkButtonAvailable(boolean okButtonAvailable) {
        this.okButtonAvailable = okButtonAvailable;
    }

    public void setHelpButtonAvailable(boolean helpButtonAvailable) {
        this.helpButtonAvailable = helpButtonAvailable;
    }

    public void setReviewButtonAvailable(boolean reviewButtonAvailable) {
        this.reviewButtonAvailable = reviewButtonAvailable;
    }

    public void setVolumeButtonAvailable(boolean volumeButtonAvailable) {
        this.volumeButtonAvailable = volumeButtonAvailable;
    }

    public void setContinueButtonAvailable(boolean continueButtonAvailable) {
        this.continueButtonAvailable = continueButtonAvailable;
    }

    public void setGoToQuestionButtonAvailable(boolean goToQuestionButtonAvailable) {
        this.goToQuestionButtonAvailable = goToQuestionButtonAvailable;
    }

    public void setReturnButtonAvailable(boolean returnButtonAvailable) {
        this.returnButtonAvailable = returnButtonAvailable;
    }

    public void setTimerLabelAvailable(boolean timerLabelAvailable) {
        this.timerLabelAvailable = timerLabelAvailable;
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

    public boolean isBackButtonEnabled() {
        return backButtonEnabled;
    }

    public boolean isOkButtonEnabled() {
        return okButtonEnabled;
    }

    public boolean isHelpButtonEnabled() {
        return helpButtonEnabled;
    }

    public boolean isReviewButtonEnabled() {
        return reviewButtonEnabled;
    }

    public boolean isVolumeButtonEnabled() {
        return volumeButtonEnabled;
    }

    public void setNextButtonEnabled(boolean nextButtonEnabled) {
        this.nextButtonEnabled = nextButtonEnabled;
    }

    public void setBackButtonEnabled(boolean backButtonEnabled) {
        this.backButtonEnabled = backButtonEnabled;
    }

    public void setOkButtonEnabled(boolean okButtonEnabled) {
        this.okButtonEnabled = okButtonEnabled;
    }

    public void setHelpButtonEnabled(boolean helpButtonEnabled) {
        this.helpButtonEnabled = helpButtonEnabled;
    }

    public void setReviewButtonEnabled(boolean reviewButtonEnabled) {
        this.reviewButtonEnabled = reviewButtonEnabled;
    }

    public void setVolumeButtonEnabled(boolean volumeButtonEnabled) {
        this.volumeButtonEnabled = volumeButtonEnabled;
    }
}
