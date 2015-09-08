package com.mocktpo.audio;

import javax.media.*;
import javax.media.format.AudioFormat;
import javax.swing.*;
import java.net.URL;

public class AudioWorker extends SwingWorker<Void, Void> {

    public AudioWorker(URL url) {
        this.url = url;
    }

    @Override
    public Void doInBackground() {
        if (this.audioPlayer == null) {
            Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
            Format input2 = new AudioFormat(AudioFormat.MPEG);
            Format output = new AudioFormat(AudioFormat.LINEAR);
            PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]{input1, input2}, new Format[]{output}, PlugInManager.CODEC);

            try {
                this.audioPlayer = Manager.createPlayer(new MediaLocator(this.url));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        audioPlayer.start();

        return null;
    }

    @Override
    public void done() {
        // Go to the next view.
    }

    private Player audioPlayer;
    private URL url;
}
