package pl.edu.agh.hangman;

import pl.edu.agh.hangman.gamestatus.PlayerStatus;
import pl.edu.agh.hangman.gamestatus.HangmanPicture;
import pl.edu.agh.hangman.tools.LetterReader;
import pl.edu.agh.hangman.tools.WordToGuess;
import pl.edu.agh.hangman.words.*;
import pl.edu.agh.hangman.words.choose.WordsChooseStrategy;
import pl.edu.agh.hangman.words.printer.WordPrinter;
import pl.edu.agh.hangman.words.printer.WordPrinterSimple;
import pl.edu.agh.hangman.words.provider.WordsProvider;

class Hangman {
    private GameSettings gameSettings = new GameSettings(this);
    private PlayerStatus playerStatus = new HangmanPicture();
    private WordToGuess wordToGuess;
    private Words words;
    private final LetterReader letterReader = new LetterReader();
    private WordPrinter wordPrinter;

    public void showWelcomeScreen() {
        System.out.println("\n..:THE HANGMAN GAME:..");
        playerStatus.printLogo();
    }

    public void showGameMenu() {
        gameSettings.run();
    }

    void setUpGame(WordsProvider wordsProvider, WordsChooseStrategy wordsChooseStrategy) {
        this.words = new Words(wordsProvider, wordsChooseStrategy);
        wordToGuess = new WordToGuess(words.getWord());
        wordPrinter = new WordPrinterSimple(wordToGuess.toString());
    }

    void showStartingGameScreen() {
        System.out.println("You have 6 lifes - lets play the hangman game...");
        playerStatus.setFullLifes();
        playerStatus.printLifes();
    }

    void play() {
        System.out.println(wordToGuess);
        String userLetters = "";
        String givenLetter;
        wordPrinter.print("");
        do {
            givenLetter = letterReader.getFromUser();
            if (wordToGuess.contains(givenLetter)) {
                playerStatus.printLifes();
                userLetters = userLetters.concat(givenLetter);
                wordPrinter.print(userLetters);
                if (wordToGuess.canBeCreatedBy(userLetters)) {
                    playerStatus.setWon();
                    return;
                }
            } else {
                playerStatus.oneLifeLost();
                playerStatus.printLifes();
                wordPrinter.print(userLetters);
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
            System.out.printf("This was %s\n\n\n", wordToGuess);
        }
        letterReader.pressAnyKeyToContinue();
    }

    void setUpBeforeCloseTheGame() {
        letterReader.closeScanner();
    }

    public void endGame() {
        System.out.println("Good bye :-)");
        System.exit(0);
    }
}