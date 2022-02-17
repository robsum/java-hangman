package pl.edu.agh.hangman;

import pl.edu.agh.hangman.gamesettings.GameSettings;
import pl.edu.agh.hangman.gamesettings.PlayerStatus;
import pl.edu.agh.hangman.gamesettings.HangmanPicture;
import pl.edu.agh.hangman.tools.LetterReader;
import pl.edu.agh.hangman.tools.WordToGuess;
import pl.edu.agh.hangman.words.*;
import pl.edu.agh.hangman.words.choose.NoWordFoundException;
import pl.edu.agh.hangman.words.choose.WordsChooseStrategy;
import pl.edu.agh.hangman.words.printer.WordPrinter;
import pl.edu.agh.hangman.words.printer.WordPrinterSimple;
import pl.edu.agh.hangman.words.provider.WordsProvider;

public class Hangman {
    private GameSettings gameSettings = new GameSettings(this);
    private PlayerStatus playerStatus = new HangmanPicture();
    private WordToGuess wordToGuess;
    private Words words;
    private final LetterReader letterReader = new LetterReader();
    private WordPrinter wordPrinter;

    void showWelcomeScreen() {
        System.out.println("\n..:THE HANGMAN GAME:..");
        playerStatus.printLogo();
    }

    public void showGameMenu() {
        gameSettings.run();
    }

    public void setUpGame(WordsProvider wordsProvider, WordsChooseStrategy wordsChooseStrategy) {
        this.words = new Words(wordsProvider, wordsChooseStrategy);
        try {
            wordToGuess = new WordToGuess(words.getWord());
        } catch (NoWordFoundException e) {
            System.out.println("\t\t\t\t" + e.getMessage());
            System.out.println("\t\t\t\tFix it some how in settings.");
            showGameMenu();
            return;
        }
        wordPrinter = new WordPrinterSimple(wordToGuess.toString());
    }

    void showStartingGameScreen() {
        System.out.println("You have 6 lifes - lets play the hangman game...");

        playerStatus.resetToInitialState();
        playerStatus.printLifes();
        wordPrinter.print("");
    }

    void play() {
        System.out.println(wordToGuess); // FIXME delete this
        StringBuilder userLetters = new StringBuilder();
        String givenLetter;
        do {
            givenLetter = letterReader.getFromUser();
            if (wordToGuess.contains(givenLetter)
                    && !userLetters.toString().matches(".*" + givenLetter + ".*")) {
                userLetters.append(givenLetter);
                if (wordToGuess.canBeCreatedBy(userLetters.toString())) {
                    playerStatus.setWon();
                }
            } else {
                playerStatus.oneLifeLost();
            }
            playerStatus.printLifes();
            wordPrinter.print(userLetters.toString());
        } while (playerStatus.hasLifeToPlay() && !playerStatus.isWinner());
    }

    void printSummary() {
        System.out.println("------------------------\n");
        playerStatus.printLifes();
        if (playerStatus.isWinner()) {
            System.out.println("You have won! :-)");
        } else {
            System.out.println("You have lost! :-(");
            System.out.printf("This was %s\n\n\n", wordToGuess);
        }
        letterReader.pressAnyKeyToContinue();
    }

    public void setUpBeforeCloseTheGame() {
        letterReader.closeScanner();
    }

    public void endGame() {
        System.out.println("Good bye :-)");
        System.exit(0);
    }
}