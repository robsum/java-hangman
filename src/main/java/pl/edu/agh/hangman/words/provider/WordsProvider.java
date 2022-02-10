package pl.edu.agh.hangman.words.provider;

import java.util.List;

public interface WordsProvider {
    List<String> getWords();
}