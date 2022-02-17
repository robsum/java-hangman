package pl.edu.agh.hangman.words.choose;

import java.util.List;

public interface WordsChooseStrategy {
    String getWord(List<String> words) throws NoWordFoundException;
}