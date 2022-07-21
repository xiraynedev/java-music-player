package com.xiraynedev;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        var musicPlayer = new MusicPlayer();
        musicPlayer.startPlayer();
    }
}
