package pl.edu.agh.hangman;

import pl.edu.agh.hangman.gamestatus.GameStatus;
import pl.edu.agh.hangman.gamestatus.HangmanPicture;
import pl.edu.agh.hangman.tools.LetterUserInputProvider;
import pl.edu.agh.hangman.tools.WordAnalyzer;
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

        LetterUserInputProvider letterUserInputProvider = new LetterUserInputProvider();

        WordPrinter wordPrinter = new WordPrinterSimple(wordToGuess);
        String guessedChars = new String("");

        WordAnalyzer wordAnalyzer = new WordAnalyzer(wordToGuess);

        String ch = letterUserInputProvider.getLetterFromUser();
        while (hangmanPicture.isAlive()) {
            if (wordAnalyzer.doesContainALetter(ch)) {
                // print word with guessed chars
                guessedChars = guessedChars.concat(ch);
                wordPrinter.print(guessedChars);
                boolean allGuessed = wordAnalyzer.allGuessed(guessedChars);
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
            ch = letterUserInputProvider.getLetterFromUser();
        }

        if (!hangmanPicture.isAlive()) {
            System.out.println("Przegrałeś!");
        } else {
            System.out.println("Wygrałeś!");
        }
    }
}