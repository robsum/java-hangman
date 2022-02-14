package pl.edu.agh.hangman.words.choose;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ByLengthWordChoose implements WordsChooseStrategy {
    private final int length;

    public ByLengthWordChoose(int length) {
        this.length = length;
    }

    @Override
    public String getWord(List<String> words) {
        // TODO this is quick implementation for test only
        if(words.size() == 0 ) {
            //FIXME later
            throw new NoWordFoundException("Words were not set up properly.");
        }
        List<String> wordsWithGivenLength = words.stream()
                .filter(s -> s.length() == length)
                .collect(Collectors.toList());
        if(wordsWithGivenLength.size() == 0) {
            throw new NoWordFoundException("There are no words with such lenght = " + length + ".");
        }

        Random random = new Random();
        int id = random.nextInt(wordsWithGivenLength.size());
        return wordsWithGivenLength.get(id);
    }
}