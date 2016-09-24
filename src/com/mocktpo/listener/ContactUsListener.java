package com.mocktpo.listener;

import com.mocktpo.widget.dialog.ContactUsDialog;
import com.mocktpo.window.AppWindow;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class ContactUsListener extends MyListener implements HyperlinkListener {

    /* Application Window */

    protected AppWindow appWindow;

    public ContactUsListener(AppWindow appWindow) {
        this.setAppWindow(appWindow);
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ContactUsDialog contactUs = new ContactUsDialog(ContactUsListener.this.getAppWindow(), "", true);
                    contactUs.setVisible(true);
                }
            });
        }
    }

    public AppWindow getAppWindow() {
        return appWindow;
    }

    public void setAppWindow(AppWindow appWindow) {
        this.appWindow = appWindow;
    }
}
