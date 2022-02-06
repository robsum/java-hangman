package pl.edu.agh.hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface WordsProvider {
    List<String> getWords() throws IOException;

}
