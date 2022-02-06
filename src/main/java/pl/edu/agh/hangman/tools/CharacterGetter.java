package pl.edu.agh.hangman.tools;

import java.util.Locale;
import java.util.Scanner;

public class CharacterGetter {
    public String getCharacterFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("guess> ");
        char ch = scanner.next().toUpperCase(Locale.ROOT).charAt(0);
        return String.valueOf(ch);
    }
}
