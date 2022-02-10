package pl.edu.agh.hangman.gamestatus;

public interface PlayerStatus {
    void printLifes();

    void printLogo();

    void oneLifeLost();

    boolean hasLifeToPlay();

    void setWon();

    void setLost();

    boolean isWinner();

    void setFullLifes();
}
