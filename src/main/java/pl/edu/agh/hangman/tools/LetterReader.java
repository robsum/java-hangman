package pl.edu.agh.hangman.tools;

import java.util.Scanner;

public class LetterReader {
    private static final String USER_PROMPT = "guess> ";
    private Scanner scanner = new Scanner(System.in);

    public String getFromUser() {
        System.out.println(USER_PROMPT);
        return scanner.nextLine().toUpperCase().substring(0, 1);
    }

    public void pressAnyKeyToContinue() {
        System.out.println("Press any ket to continue...");
        scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }
}