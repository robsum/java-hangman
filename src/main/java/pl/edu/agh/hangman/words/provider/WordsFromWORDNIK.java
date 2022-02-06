package pl.edu.agh.hangman.words.provider;

import java.io.IOException;
import java.util.List;

public class WordsFromWORDNIK implements WordsProvider {
    @Override
    public List<String> getWords() throws IOException {
        //TODO read words from http://api.wordnik.com
        return null;
    }
}
