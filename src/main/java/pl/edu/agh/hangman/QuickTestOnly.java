package pl.edu.agh.hangman;

public class QuickTestOnly {
    public static void main(String[] args) {

        String word = " rumpur";

        String substring = word.toUpperCase().substring(0, 1);
        System.out.printf("Word is >>%s<<", substring);
    }
}
