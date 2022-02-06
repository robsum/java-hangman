package pl.edu.agh.hangman;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getRandomWord() {
        int index = getRandomNumber(0, words.size());
        return words.get(index);
    }

    public String getWord() {
        return getRandomWord().toUpperCase(Locale.ROOT);
    }
}
