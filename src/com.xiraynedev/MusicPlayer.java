package com.xiraynedev;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MusicPlayer {
    private Scanner scanner = new Scanner(System.in);
    private File file;
    private AudioInputStream stream;
    private Clip clip;

    public void startPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        file = new File("sample_music/1.wav");
        stream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(stream);
        startPlayerInterface();
    }

    private void changeSong() {
        String filepath = "sample_music/" + (new Random().nextInt(5) + 1) + ".wav";
        file = new File(filepath);
    }

    private void startPlayerInterface() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        char response = '0';
        String filename = file.getName();

        System.out.println("Welcome to the music player in Java! Below are the options for controlling music playback.");

        while (!(response == 'q')) {
            System.out.println("Enter p for play; s for stop; r for reset; c to change songs; q for quit;");

            response = scanner.next().toLowerCase().charAt(0);

            switch (response) {
                case 'p':
                    if (clip.getMicrosecondLength() > 0 && !clip.isRunning()) {
                        clip.setMicrosecondPosition(0);
                        clip.start();
                    }
                    if (!clip.isRunning()) {
                        System.out.println("Now playing..." + filename);
                        clip.start();
                    }
                    break;
                case 's':
                    if (clip.isRunning()) {
                        System.out.println("Now stopping..." + filename);
                        clip.stop();
                    }
                    break;
                case 'r':
                    if (clip.isRunning())
                        clip.stop();
                    System.out.println("Now resetting..." + filename);
                    clip.setMicrosecondPosition(0);
                    clip.start();
                    break;
                case 'c':
                    if (clip.isRunning())
                        clip.stop();
                    changeSong();
                    filename = file.getName();
                    stream = AudioSystem.getAudioInputStream(file);
                    clip.close();
                    clip.open(stream);
                    clip.start();
                    System.out.println("Now playing..." + filename);
                    break;
                case 'q':
                    if (clip.isRunning())
                        clip.stop();
                    clip.close();
                    System.out.println("Now exiting player...");
                    scanner.close();
                    clip.close();
                    break;
                default:
                    System.out.println("Please enter a valid response.");
                    break;
            }
        }
    }
}
