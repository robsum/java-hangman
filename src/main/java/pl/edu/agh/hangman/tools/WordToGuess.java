package pl.edu.agh.hangman.tools;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordToGuess {
    private String word;

    public WordToGuess(String word) {
        this.word = word;
    }

    public boolean canBeCreatedBy(String givenLetters) {
        Set<Character> guessed = new LinkedHashSet<>();
        for (int i = 0; i < givenLetters.length(); ++i) {
            guessed.add(givenLetters.charAt(i));
        }
        Set<Character> lettersFromWordToGuess = new LinkedHashSet<>();
        for (int i = 0; i < word.length(); ++i) {
            lettersFromWordToGuess.add(word.charAt(i));
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

    public boolean contains(String letter) {
        return word.contains(String.valueOf(letter));
    }

    @Override
    public String toString() {
        return word;
    }
}