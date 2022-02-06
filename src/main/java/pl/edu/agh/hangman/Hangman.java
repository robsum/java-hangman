package pl.edu.agh.hangman;

import java.io.IOException;

import java.util.Locale;

public class Hangman {

    public static final String[] HANGMANPICS = new String[]{
            "  +---+\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",
            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",
            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",
            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",
            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",
            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " /    |\n" +
                    "      |\n" +
                    "=========",
            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " / \\  |\n" +
                    "      |\n" +
                    "========"
    };

    public static void main(String[] args) throws IOException {
        // Hangman Pics: 0 - 6
        int wrongAnswers = 0;

        System.out.println(HANGMANPICS[wrongAnswers]);

        Words words = new Words();
        String wordToGuess = words.getRandomWord().toUpperCase(Locale.ROOT);

        System.out.println(wordToGuess);

        CharacterGetter characterGetter = new CharacterGetter();

        WordPrinter wordPrinter = new WordPrinterSimple(wordToGuess);
        String guessedChars = new String;

        String ch = characterGetter.getCharacterFromUser();
        while (!ch.equals("Q") && wrongAnswers < 6) {
            if (CharacterGuesser.isCharacterInString(ch, wordToGuess)) {
                // print word with guessed chars
                guessedChars = guessedChars.concat(ch);
                wordPrinter.print(guessedChars);

            } else {
                wrongAnswers++;
                System.out.println(HANGMANPICS[wrongAnswers]);
            }
            if (wrongAnswers == 6) {
                break;
            }
            ch = characterGetter.getCharacterFromUser();
        }

        if (wrongAnswers == 6) {
            System.out.println("Przegrałeś!");
        } else {
            System.out.println("Wygrałeś!");
        }


    }
    }
}