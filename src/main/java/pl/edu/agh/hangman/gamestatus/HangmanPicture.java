package pl.edu.agh.hangman.gamestatus;

public class HangmanPicture implements PlayerStatus {
    private int currentWordGuessCounter = 0;
    private boolean hasPlayerWon = false;
    private final static int MAX_GUESSES = 6;
    private final static String[] HANGMANPICS = new String[]{
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

    @Override
    public void printLifes() {
        if (currentWordGuessCounter <= MAX_GUESSES) {
            System.out.println(HANGMANPICS[currentWordGuessCounter]);
        } else {
            System.out.println(HANGMANPICS[MAX_GUESSES]);
        }
    }

    @Override
    public void oneLifeLost() {
        currentWordGuessCounter++;
    }

    @Override
    public boolean hasLifeToPlay() {
        return currentWordGuessCounter < MAX_GUESSES;
    }

    @Override
    public void setWon() {
        hasPlayerWon = true;
    }

    @Override
    public void setLost() {
        hasPlayerWon = false;
    }

    @Override
    public boolean isWinner() {
        return hasPlayerWon;
    }
}