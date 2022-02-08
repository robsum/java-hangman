package pl.edu.agh.hangman.tools;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordAnalyzer {
    private String wordToGuess;

    public WordAnalyzer(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public boolean allGuessed(String guessedChars) {
        Set<Character> setGuessedChars = new LinkedHashSet<>();
        for (int i = 0; i < guessedChars.length(); ++i) {
            setGuessedChars.add(guessedChars.charAt(i));
        }
        Set<Character> setWordToGuess = new LinkedHashSet<>();
        for (int i = 0; i < wordToGuess.length(); ++i) {
            setWordToGuess.add(wordToGuess.charAt(i));
        }
        int desiredAmount = setWordToGuess.size();
        int actualAmount = 0;
        for (Character ch : setGuessedChars) {
            if (setWordToGuess.contains(ch)) {
                actualAmount++;
            }
        }
        return actualAmount == desiredAmount;
    }

    public boolean doesContainALetter(String letter) {
        return wordToGuess.contains(String.valueOf(letter));
    }
}
