package pl.edu.agh.hangman;

import java.util.List;

public interface WordsChooseStrategy {
    String getWord(List<String> words);
}
