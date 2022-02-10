package pl.edu.agh.hangman.tools;

import java.util.Locale;
import java.util.Scanner;

public class LetterReader {
    private static final String USER_PROMPT = "guess> ";
    private Scanner scanner = new Scanner(System.in);

    public String getLetterFromUser() {
        System.out.println(USER_PROMPT);
        char ch = scanner.next().toUpperCase(Locale.ROOT).charAt(0);
        scanner.nextLine();
        return String.valueOf(ch);
    }

    public void pressAnyKeyToContinue(){
        System.out.println("Press any ket to continue...");
        scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }
}