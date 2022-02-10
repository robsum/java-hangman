package pl.edu.agh.hangman;

import pl.edu.agh.hangman.gamestatus.PlayerStatus;
import pl.edu.agh.hangman.gamestatus.HangmanPicture;
import pl.edu.agh.hangman.tools.LetterReader;
import pl.edu.agh.hangman.tools.LettersChecker;
import pl.edu.agh.hangman.words.*;
import pl.edu.agh.hangman.words.choose.WordsChooseStrategy;
import pl.edu.agh.hangman.words.printer.WordPrinter;
import pl.edu.agh.hangman.words.printer.WordPrinterSimple;
import pl.edu.agh.hangman.words.provider.WordsProvider;

class Hangman {
    private GameSettings gameSettings = new GameSettings(this);
    private PlayerStatus playerStatus = new HangmanPicture();
    private Words wordsFromFile;
    private final LetterReader letterReader = new LetterReader();
    private WordPrinter wordPrinter;
    private LettersChecker lettersChecker;
    private String wordToGuess;

    public void showWelcomeScreen() {
        System.out.println("\n..:THE HANGMAN GAME:..");
        playerStatus.printLogo();
    }

    public void showGameMenu() {
        gameSettings.run();
    }

    void setUpGame(WordsProvider wordsProvider, WordsChooseStrategy wordsChooseStrategy) {
        this.wordsFromFile = new Words(wordsProvider, wordsChooseStrategy);
        wordToGuess = wordsFromFile.getWord();
        wordPrinter = new WordPrinterSimple(wordToGuess);
        lettersChecker = new LettersChecker(wordToGuess);
    }

    void showStartingGameScreen() {
        System.out.println("You have 6 lifes - lets play the hangman game...");
        playerStatus.setFullLifes();
        playerStatus.printLifes();
    }

    void play() {
        System.out.println(wordToGuess);
        String allLetters = "";
        String currentLetter;
        wordPrinter.print("");
        do {
            currentLetter = letterReader.getLetterFromUser();
            if (lettersChecker.doesContainALetter(currentLetter)) {
                allLetters = allLetters.concat(currentLetter);
                playerStatus.printLifes();
                wordPrinter.print(allLetters);
                if (lettersChecker.allGuessed(allLetters)) {
                    playerStatus.setWon();
                    return;
                }
            } else {
                playerStatus.oneLifeLost();
                playerStatus.printLifes();
                wordPrinter.print(allLetters);
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