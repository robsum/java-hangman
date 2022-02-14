package pl.edu.agh.hangman.words.choose;

public class NoWordFoundException extends RuntimeException {
    public NoWordFoundException(String message) {
        super(message);
    }
}
