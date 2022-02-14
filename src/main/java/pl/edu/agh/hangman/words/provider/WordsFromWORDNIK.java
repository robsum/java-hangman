package pl.edu.agh.hangman.words.provider;

import java.util.ArrayList;
import java.util.List;

public class WordsFromWORDNIK implements WordsProvider {
    @Override
    public List<String> getWords() {
        //TODO read words from http://api.wordnik.com
        List<String> words = new ArrayList<>();
        words.add("Wordnik word A".toUpperCase());
        words.add("Wordnik word B".toUpperCase());
        words.add("Wordnik word C".toUpperCase());
        return words;
    }
}
