package pl.edu.agh.hangman;

import java.io.IOException;

public class RunApp {
    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        hangman.setUpBeforePlay();
        hangman.showStartingScreen();
        hangman.play();
        hangman.printSummary();
        hangman.setUpBeforeClose();
    }
}
