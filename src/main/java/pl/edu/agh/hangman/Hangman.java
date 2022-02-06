package pl.edu.agh.hangman;

import pl.edu.agh.hangman.gamestatus.GameStatus;
import pl.edu.agh.hangman.gamestatus.HangmanPicture;
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

        CharacterGetter characterGetter = new CharacterGetter();

        WordPrinter wordPrinter = new WordPrinterSimple(wordToGuess);
        String guessedChars = new String("");

        WordAnalyzer wordAnalyzer = new WordAnalyzer(wordToGuess);

        String ch = characterGetter.getCharacterFromUser();
        while (hangmanPicture.isAlive()) {
            if (CharacterGuesser.isCharacterInString(ch, wordToGuess)) {
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
            ch = characterGetter.getCharacterFromUser();
        }

        if (!hangmanPicture.isAlive()) {
            System.out.println("Przegrałeś!");
        } else {
            System.out.println("Wygrałeś!");
        }
    }
}