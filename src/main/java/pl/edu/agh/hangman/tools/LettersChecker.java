package pl.edu.agh.hangman.tools;

import java.util.LinkedHashSet;
import java.util.Set;

public class LettersChecker {
    private String wordToGuess;

    public LettersChecker(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public boolean allGuessed(String givenLetters) {
        Set<Character> guessed = new LinkedHashSet<>();
        for (int i = 0; i < givenLetters.length(); ++i) {
            guessed.add(givenLetters.charAt(i));
        }
        Set<Character> lettersFromWordToGuess = new LinkedHashSet<>();
        for (int i = 0; i < wordToGuess.length(); ++i) {
            lettersFromWordToGuess.add(wordToGuess.charAt(i));
        }
        int desiredAmount = lettersFromWordToGuess.size();
        int actualAmount = 0;
        for (Character letter : guessed) {
            if (lettersFromWordToGuess.contains(letter)) {
                actualAmount++;
            }
        }
        return actualAmount == desiredAmount;
    }

    public boolean doesContainALetter(String letter) {
        return wordToGuess.contains(String.valueOf(letter));
    }
}