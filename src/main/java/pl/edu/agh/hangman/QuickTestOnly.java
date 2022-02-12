package pl.edu.agh.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickTestOnly {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("Alicja 1");
        words.add("Alicja 2");
        words.add("Alicja 3");
        words.add("Alicja 4");

        Random random = new Random();

        for (int i = 1; i <= 50; i++) {
            int id = random.nextInt(words.size());
//            System.out.printf("size = %d.  id = %d\n", words.size(), id);
            String word = words.get(id);
            System.out.printf("Word: %s\n", word);
        }


    }
}
