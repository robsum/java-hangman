package pl.edu.agh.hangman;

import java.util.regex.Pattern;
import java.util.stream.Stream;

public class QuickTestOnly {
    public static void main(String[] args) {
        StringBuilder wordToPrint = new StringBuilder();
        String word = "Ala ma kota";
        String letters = "abca xcosk";

        word.toUpperCase().chars().forEach(c -> {
                    char letterInWord = (char) c;
                    if(Pattern.compile(String.valueOf(letterInWord)).matcher(letters.toUpperCase()).find()) {
                        wordToPrint.append(letterInWord + " ");
                    } else {
                        wordToPrint.append(" _ ");
                    }
                }
        );

        System.out.println(wordToPrint.toString());
    }
}
