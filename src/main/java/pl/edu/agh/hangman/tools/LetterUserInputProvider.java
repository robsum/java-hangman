package pl.edu.agh.hangman.tools;

import java.util.Locale;
import java.util.Scanner;

public class LetterUserInputProvider {
    private static final String USER_PROMPT = "guess> ";
    private Scanner scanner = new Scanner(System.in);

    public String getLetterFromUser() {
        System.out.println(USER_PROMPT);
        char ch = scanner.next().toUpperCase(Locale.ROOT).charAt(0);
        return String.valueOf(ch);
    }

    public void closeScanner() {
        scanner.close();
    }
}