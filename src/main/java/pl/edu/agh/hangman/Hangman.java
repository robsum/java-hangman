package pl.edu.agh.hangman;

import java.io.IOException;

import java.util.Locale;

public class Hangman {
    public static void main(String[] args) throws IOException {
        GameStatus hangmanPicture = new HangmanPicture();
        hangmanPicture.printLifeStatus();

        Words words = new Words();
        String wordToGuess = words.getRandomWord().toUpperCase(Locale.ROOT);

        System.out.println(wordToGuess);

        CharacterGetter characterGetter = new CharacterGetter();

        WordPrinter wordPrinter = new WordPrinterSimple(wordToGuess);
        String guessedChars = new String();

        String ch = characterGetter.getCharacterFromUser();
        while (hangmanPicture.isAlive()) {
            if (CharacterGuesser.isCharacterInString(ch, wordToGuess)) {
                // print word with guessed chars
                guessedChars = guessedChars.concat(ch);
                wordPrinter.print(guessedChars);
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