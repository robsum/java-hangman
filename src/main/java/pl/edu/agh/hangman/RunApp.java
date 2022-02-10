package pl.edu.agh.hangman;

public class RunApp {
    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        while (true) {
            hangman.showWelcomeScreen();
            hangman.showGameMenu();
            hangman.showStartingGameScreen();
            hangman.play();
            hangman.printSummary();
        }
    }
}