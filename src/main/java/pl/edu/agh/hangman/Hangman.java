package pl.edu.agh.hangman;

import java.io.IOException;

public class Hangman {
    public static void main(String[] args) throws IOException {
        //FIXME: hangman picture quick tests only
        GameStatus hangmanPicture = new HangmanPicture();
        hangmanPicture.printLifeStatus();
        hangmanPicture.oneLifeLost();
        hangmanPicture.oneLifeLost();
        hangmanPicture.oneLifeLost();
        hangmanPicture.oneLifeLost();
        hangmanPicture.oneLifeLost();
        hangmanPicture.oneLifeLost();
        hangmanPicture.oneLifeLost();
        hangmanPicture.oneLifeLost();
        hangmanPicture.oneLifeLost();
        hangmanPicture.oneLifeLost();
        hangmanPicture.printLifeStatus();

        Words words = new Words();
        String wordToGuess = words.getRandomWord();

        System.out.println(wordToGuess);

        // FIXME to test only
        WordPrinter wordPrinter = new WordPrinterSimple(wordToGuess);
        wordPrinter.print("etoalama");
    }
}