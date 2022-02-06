package pl.edu.agh.hangman;

import java.io.IOException;
import java.util.List;

public class Words {
    private WordsProvider wordsProvider;
    private WordsChooseStrategy wordsChooseStrategy;
    private List<String> words;

    public Words(WordsProvider wordsProvider, WordsChooseStrategy wordsChooseStrategy) {
        this.wordsProvider = wordsProvider;
        this.wordsChooseStrategy = wordsChooseStrategy;
        setWords();
    }

    private void setWords() {
        try {
            words = wordsProvider.getWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getWord() {
        return wordsChooseStrategy.getWord(words);
    }
}
