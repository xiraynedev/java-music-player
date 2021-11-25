package com.xiraynedev;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.*;

public class Main {

  public static File changeTracks() {
    String filePath = "sample_music/" + (new Random().nextInt(5) + 1) + ".wav";
    return new File(filePath);
  }

  public static void main(String[] args)
    throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    Scanner scanner = new Scanner(System.in);

    File file = new File("sample_music/1.wav");
    AudioInputStream stream = AudioSystem.getAudioInputStream(file);
    Clip clip = AudioSystem.getClip();
    clip.open(stream);

    String response = "";

    System.out.println(
      "Welcome to the music player in Java! Below are the options for controlling music playback."
    );

    while (!response.equals("q")) {
      System.out.println(
        "Enter p for play; s for stop; r for reset; t to change songs; q for quit;"
      );

      response = scanner.next();
      response = response.toLowerCase();

      switch (response) {
        case "p":
          if (!clip.isRunning()) {
            System.out.println("Now playing..." + file.getName());
            clip.start();
          }
          break;
        case "s":
          if (clip.isRunning()) {
            System.out.println("Now stopping..." + file.getName());
            clip.stop();
          }
          break;
        case "r":
          if (clip.isRunning()) {
            clip.stop();
          }
          System.out.println("Now resetting..." + file.getName());
          clip.setMicrosecondPosition(0);
          clip.start();
          break;
        case "q":
          if (clip.isRunning()) {
            clip.stop();
          }
          clip.close();
          break;
        case "t":
          if (clip.isRunning()) {
            clip.stop();
          }
          file = changeTracks();
          stream = AudioSystem.getAudioInputStream(file);
          clip.close();
          clip.open(stream);
          clip.start();
          System.out.println("Now playing..." + file.getName());
          break;
        default:
          System.out.println("Please enter a valid response.");
          break;
      }
    }
    System.out.println("Now exiting player...");
    scanner.close();
    clip.close();
  }
}
