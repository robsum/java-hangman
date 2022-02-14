package pl.edu.agh.hangman.words.provider;

import java.util.ArrayList;
import java.util.List;

public class WordsGivenByUser implements WordsProvider {
    private final ArrayList<String> words;

    public WordsGivenByUser(ArrayList<String> words) {
        this.words = words;
    }

    @Override
    public List<String> getWords() {
        return words;
    }
}