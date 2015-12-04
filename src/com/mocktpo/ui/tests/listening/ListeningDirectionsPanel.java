package com.mocktpo.ui.tests.listening;

import com.mocktpo.ui.widgets.BodyPanel;
import com.mocktpo.util.GlobalConstants;

import javax.media.*;
import javax.media.format.AudioFormat;
import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ListeningDirectionsPanel extends BodyPanel implements ActionListener {

    private JEditorPane descriptionPane;
    private Player audioPlayer;
    private Timer timer;

    public ListeningDirectionsPanel(Rectangle bounds) {
        super(bounds);
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        this.setDescriptionPane();

        this.setAudioPlayer();
    }

    protected void setDescriptionPane() {
        this.descriptionPane = new JEditorPane();

        this.descriptionPane.setBounds(0, 0, this.getWidth(), this.getHeight());

        this.descriptionPane.setEditable(false);
        this.descriptionPane.setOpaque(false);

        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet style = kit.getStyleSheet();
        style.addRule(".ld-header { color: #3d4167; font-family: Georgia; font-size: 18px; font-weight: bold; text-align: center; margin-top: 60px; margin-bottom: 40px; } .ld { color: #333333; font-family: Arial; font-size: 12px; margin-left: 24px; margin-right: 24px; margin-bottom: 12px; } .ld-footer { color: #333333; font-family: Arial; font-size: 12px; font-style: italic; text-align: center; }");
        this.descriptionPane.setEditorKit(kit);
        String text = "<div class='ld-header'>Listening Section Directions</div>";
        text += "<div class='ld'>This test measures your ability to understand conversations and lectures in English.</div>";
        text += "<div class='ld'>The Listening section is divided into 2 separately timed parts. In each part you will listen to 1 conversation and 2 lectures. You will hear each conversation or lecture only <b>one</b> time.</div>";
        text += "<div class='ld'>After each conversation or lecture, you will answer some questions about it. The questions typically ask about the main idea and supporting details. Some questions ask about a speaker's purpose or attitude. Answer the questions based on what is stated or implied by the speakers.</div>";
        text += "<div class='ld'>You may take notes while you listen. You may use your notes to help you answer the questions. Your notes will <b>not</b> be scored.</div>";
        text += "<div class='ld'>If you need to change the volume while you listen, click on the <b>Volume</b> icon at the top of the screen.</div>";
        String imgUrl = this.getClass().getResource(GlobalConstants.IMAGES_DIR + "earphone.png").toString();
        text += "<div class='ld'>In some questions you will see this icon: <img src='" + imgUrl + "' />This means that you will hear, but not see, part of the question.</div>";
        text += "<div class='ld'>Some of the questions have special directions. These directions appear in a gray box on the screen.</div>";
        text += "<div class='ld'>Most questions are worth 1 point. If a question is worth more than 1 point, it will have special directions that indicate how many points you can receive.</div>";
        text += "<div class='ld'>You must answer each question. After you answer, click on <b>Next</b>. Then click on <b>OK</b> to confirm your answer and go on to the next question. After you click on <b>OK</b>, you cannot return to previous questions. If you are using the <b>Untimed Mode</b>, you may return to previous questions and you may listen to each conversation and lecture again. Remember that prior exposure to the conversations, lectures, and questions could lead to an increase in your section scores and may not reflect a score you would get when seeing them for the first time.</div>";
        text += "<div class='ld'>During this practice test, you may click the <b>Pause</b> icon at any time. This will stop the test until you decide to continue. You may continue the test in a few minutes or at any time during the period that your test is activated.</div>";
        text += "<div class='ld'>In an actual test, and if you are using <b>Timed Mode</b>, a clock at the top of the screen will show you how much time is remaining. The clock will not count down while you are listening. The clock will count down only while you are answering the questions.</div>";
        text += "<div class='ld-footer'>(Click on <b>Continue</b> at any time to dismiss these directions.)</div>";
        this.descriptionPane.setText(text);

        this.add(this.descriptionPane);
    }

    protected void setAudioPlayer() {
        URL url = this.getClass().getResource(GlobalConstants.AUDIOS_DIR + "listening_section_directions.mp3");
        Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
        Format input2 = new AudioFormat(AudioFormat.MPEG);
        Format output = new AudioFormat(AudioFormat.LINEAR);
        PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]{input1, input2}, new Format[]{output}, PlugInManager.CODEC);
        try {
            this.audioPlayer = Manager.createRealizedPlayer(new MediaLocator(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void startAudio() {
        this.audioPlayer.start();
        this.timer = new Timer(1000, this);
        this.timer.setActionCommand("doStartAudio");
        this.timer.start();
    }

    public void stopAudio() {
        this.audioPlayer.stop();
        this.timer.stop();
    }

    /**************************************************
     * Listeners
     **************************************************/

    public void actionPerformed(ActionEvent e) {
        if ("doStartAudio".equals(e.getActionCommand())) {
            long duration = this.audioPlayer.getDuration().getNanoseconds();
            long now = this.audioPlayer.getMediaTime().getNanoseconds();
            if (now >= duration) {
                timer.stop();
            }
        }
    }
}
