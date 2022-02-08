package pl.edu.agh.hangman;

import pl.edu.agh.hangman.gamestatus.GameStatus;
import pl.edu.agh.hangman.gamestatus.HangmanPicture;
import pl.edu.agh.hangman.tools.LetterReader;
import pl.edu.agh.hangman.tools.LettersChecker;
import pl.edu.agh.hangman.words.*;
import pl.edu.agh.hangman.words.printer.WordPrinter;
import pl.edu.agh.hangman.words.printer.WordPrinterSimple;
import pl.edu.agh.hangman.words.choose.RandomWordChoose;
import pl.edu.agh.hangman.words.provider.WordsFromFile;

import java.io.IOException;

public class Hangman {
    private GameStatus hangmanPicture;
    private Words wordsFromFile;
    private LetterReader letterReader;
    private WordPrinter wordPrinter;
    private LettersChecker lettersChecker;
    private String wordToGuess;

    public void setUpBeforePlay() {
        hangmanPicture = new HangmanPicture();
        wordsFromFile = new Words(new WordsFromFile(), new RandomWordChoose());
        letterReader = new LetterReader();

        wordToGuess = wordsFromFile.getWord();
        wordPrinter = new WordPrinterSimple(wordToGuess);
        lettersChecker = new LettersChecker(wordToGuess);
    }

    public void showStartingScreen() {
        hangmanPicture.printLifeStatus();
        System.out.println(wordToGuess);
    }

    public void play() throws IOException {
        String guessedChars = "";

        String letter = letterReader.getLetterFromUser();
        while (hangmanPicture.isAlive()) {
            if (lettersChecker.doesContainALetter(letter)) {
                // print word with guessed chars
                guessedChars = guessedChars.concat(letter);
                wordPrinter.print(guessedChars);
                boolean allGuessed = lettersChecker.allGuessed(guessedChars);
                if (allGuessed) {
                    break;
                }
            } else {
                hangmanPicture.oneLifeLost();
                hangmanPicture.printLifeStatus();
            }
            if (!hangmanPicture.isAlive()) {
                break;
            }
            letter = letterReader.getLetterFromUser();
        }

        if (!hangmanPicture.isAlive()) {
            System.out.println("You have lost! :-(");
        } else {
            System.out.println("You have won! :-)");
        }
    }

    public void setUpBeforeClose() {
        letterReader.closeScanner();
    }
}