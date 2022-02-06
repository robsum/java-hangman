package pl.edu.agh.hangman.words.choose;

import java.util.List;

public class RandomWordChoose implements WordsChooseStrategy {
    @Override
    public String getWord(List<String> words) {
        int index = getRandomNumber(0, words.size());
        return words.get(index);
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
