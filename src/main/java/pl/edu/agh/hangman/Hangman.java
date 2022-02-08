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
    public static void main(String[] args) throws IOException {
        GameStatus hangmanPicture = new HangmanPicture();
        hangmanPicture.printLifeStatus();

        Words wordsFromFile = new Words(new WordsFromFile(), new RandomWordChoose());
        String wordToGuess = wordsFromFile.getWord();

        System.out.println(wordToGuess);

        LetterReader letterReader = new LetterReader();

        WordPrinter wordPrinter = new WordPrinterSimple(wordToGuess);
        String guessedChars = new String("");

        LettersChecker lettersChecker = new LettersChecker(wordToGuess);

        String ch = letterReader.getLetterFromUser();
        while (hangmanPicture.isAlive()) {
            if (lettersChecker.doesContainALetter(ch)) {
                // print word with guessed chars
                guessedChars = guessedChars.concat(ch);
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
            ch = letterReader.getLetterFromUser();
        }

        if (!hangmanPicture.isAlive()) {
            System.out.println("Przegrałeś!");
        } else {
            System.out.println("Wygrałeś!");
        }
    }
}