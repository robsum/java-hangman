package pl.edu.agh.hangman.words.choose;

import java.util.List;

public class FirstGivenWordChoose implements WordsChooseStrategy {
    private int id = -1;

    @Override
    public String getWord(List<String> words) throws NoWordFoundException {
        //FIXME this is quick implementation only - correct this later
        if (words.size() == 0) {
            throw new NoWordFoundException("Words were not set up properly.");
        }
        id++;
        int max = words.size() - 1;
        if (id > max) {
            id = 0;
        }
        return words.get(id);
    }
}
