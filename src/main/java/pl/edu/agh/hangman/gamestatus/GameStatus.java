package pl.edu.agh.hangman.gamestatus;

public interface GameStatus {
    void printLifeStatus();

    void oneLifeLost();

    boolean isAlive();
}
