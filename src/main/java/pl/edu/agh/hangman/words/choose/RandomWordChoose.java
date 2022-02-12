package pl.edu.agh.hangman.words.choose;

import java.util.List;
import java.util.Random;

public class RandomWordChoose implements WordsChooseStrategy {
    private final Random random;

    public RandomWordChoose() {
        this.random = new Random();
    }

    @Override
    public String getWord(List<String> words) {
        int id = random.nextInt(words.size());
        return words.get(id);
    }
}