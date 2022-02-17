package pl.edu.agh.hangman.words.choose;

public class NoWordFoundException extends Exception {
    public NoWordFoundException(String message) {
        super(message);
    }
}
