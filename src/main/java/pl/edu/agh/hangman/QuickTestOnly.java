package pl.edu.agh.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class QuickTestOnly {
    public static void main(String[] args) {

        List<String> words = new ArrayList<>();
        words.add("Ala");
        words.add("Ola");
        words.add("Ula");

        StringBuilder words2 = new StringBuilder();
        words2.append("Tom,");
        words2.append("Tim,");
        words2.append("Tum");

        String results = words2.toString();


        System.out.println(results);




    }
}
