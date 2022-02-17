package pl.edu.agh.hangman.tools;

import java.util.Scanner;

public class LetterReader {
    private static final String USER_PROMPT = "guess> ";
    private Scanner scanner = new Scanner(System.in);

    public String getFromUser() {
        while (true) {
            System.out.println(USER_PROMPT);
            String userData = scanner.nextLine();
            if (userData.isEmpty()) {
                continue;
            }
            return userData.toUpperCase().substring(0, 1);
        }
    }

    public void pressAnyKeyToContinue() {
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }
}