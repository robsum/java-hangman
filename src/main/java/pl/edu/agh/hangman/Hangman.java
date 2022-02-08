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

public class Hangman {
    private PlayerStatus playerStatus;
    private Words wordsFromFile;
    private LetterReader letterReader;
    private WordPrinter wordPrinter;
    private LettersChecker lettersChecker;
    private String wordToGuess;

    public void setUpBeforePlay() {
        playerStatus = new HangmanPicture();
        wordsFromFile = new Words(new WordsFromFile(), new RandomWordChoose());
        letterReader = new LetterReader();

        wordToGuess = wordsFromFile.getWord();
        wordPrinter = new WordPrinterSimple(wordToGuess);
        lettersChecker = new LettersChecker(wordToGuess);
    }

    public void showStartingScreen() {
        playerStatus.printLifes();
        System.out.printf("//%s//\n",wordToGuess); //FIXME hide this line
    }

    public void play() {
        String allLetters = "";
        String currentLetter = "";
        do {
            currentLetter = letterReader.getLetterFromUser();
            if (lettersChecker.doesContainALetter(currentLetter)) {
                allLetters = allLetters.concat(currentLetter);
                wordPrinter.print(allLetters);
                if (lettersChecker.allGuessed(allLetters)) {
                    playerStatus.setWon();
                    return;
                }
            } else {
                playerStatus.oneLifeLost();
                playerStatus.printLifes();
            }
        } while (playerStatus.hasLifeToPlay());
        playerStatus.setLost();
    }

    public void printSummary() {
        System.out.println("------------------------\n");
        playerStatus.printLifes();
        if (playerStatus.isWinner()) {
            System.out.println("You have won! :-)");
        } else {
            System.out.println("You have lost! :-(");
        }
    }

    public void setUpBeforeClose() {
        letterReader.closeScanner();
    }
}