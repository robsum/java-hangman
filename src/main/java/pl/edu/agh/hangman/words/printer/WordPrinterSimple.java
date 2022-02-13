package pl.edu.agh.hangman.words.printer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordPrinterSimple implements WordPrinter {
    private static final char HIDDEN_LETTER = '_';
    private static final String LETTERS_SEPARATOR = " ";
    private final String word;

    public WordPrinterSimple(String word) {
        this.word = word.toUpperCase();
    }

    @Override
    public void print(String letters) {
        StringBuilder wordToPrint = getWordToPrint(letters);
        System.out.println(wordToPrint.toString());
        System.out.println();
    }

    private StringBuilder getWordToPrint(String letters) {
        StringBuilder wordToPrint = new StringBuilder();
        word.chars().forEach(letter -> {
                    if (exists((char) letter, letters)) {
                        wordToPrint.append((char) letter);
                    } else {
                        wordToPrint.append(HIDDEN_LETTER);
                    }
                    wordToPrint.append(LETTERS_SEPARATOR);
                }
        );
        return wordToPrint;
    }

    private boolean exists(char letter, String inLetters) {
        Pattern pattern = Pattern.compile(String.valueOf(letter));
        Matcher matcher = pattern.matcher(inLetters.toUpperCase());
        return matcher.find();
    }
}