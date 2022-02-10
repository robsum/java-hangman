package pl.edu.agh.hangman;

import pl.edu.agh.hangman.gamestatus.PlayerStatus;
import pl.edu.agh.hangman.gamestatus.HangmanPicture;
import pl.edu.agh.hangman.tools.LetterReader;
import pl.edu.agh.hangman.tools.LettersChecker;
import pl.edu.agh.hangman.words.*;
import pl.edu.agh.hangman.words.printer.WordPrinter;
import pl.edu.agh.hangman.words.printer.WordPrinterSimple;
import pl.edu.agh.hangman.words.choose.RandomWordChoose;
import pl.edu.agh.hangman.words.provider.WordsFromFile;

class Hangman {
    private PlayerStatus playerStatus;
    private Words wordsFromFile;
    private LetterReader letterReader;
    private WordPrinter wordPrinter;
    private LettersChecker lettersChecker;
    private String wordToGuess;

    void setUpBeforePlay() {
        playerStatus = new HangmanPicture();
        wordsFromFile = new Words(new WordsFromFile(), new RandomWordChoose());
        letterReader = new LetterReader();

        wordToGuess = wordsFromFile.getWord();
        wordPrinter = new WordPrinterSimple(wordToGuess);
        lettersChecker = new LettersChecker(wordToGuess);
    }

    void showStartingScreen() {
        System.out.println("You have 6 lifes - play the hangman.");
        playerStatus.printLifes();
    }

    void play() {
        String allLetters = "";
        String currentLetter;
        wordPrinter.print("");
        do {
            currentLetter = letterReader.getLetterFromUser();
            if (lettersChecker.doesContainALetter(currentLetter)) {
                allLetters = allLetters.concat(currentLetter);
                playerStatus.printLifes();
                wordPrinter.print(allLetters);
                if (lettersChecker.allGuessed(allLetters)) {
                    playerStatus.setWon();
                    return;
                }
            } else {
                playerStatus.oneLifeLost();
                playerStatus.printLifes();
                wordPrinter.print(allLetters);
            }
        } while (playerStatus.hasLifeToPlay());
        playerStatus.setLost();
    }

    void printSummary() {
        System.out.println("------------------------\n");
        playerStatus.printLifes();
        if (playerStatus.isWinner()) {
            System.out.println("You have won! :-)");
        } else {
            System.out.println("You have lost! :-(");
            System.out.printf("This was %s.", wordToGuess);
        }
    }

    void setUpBeforeClose() {
        letterReader.closeScanner();
    }
}