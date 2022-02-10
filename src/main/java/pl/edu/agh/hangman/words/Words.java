package pl.edu.agh.hangman.words;

import pl.edu.agh.hangman.words.choose.WordsChooseStrategy;
import pl.edu.agh.hangman.words.provider.WordsProvider;

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
        words = wordsProvider.getWords();
    }

    public String getWord() {
        return wordsChooseStrategy.getWord(words);
    }
}