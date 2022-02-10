package pl.edu.agh.hangman;

public class QuickTestOnly {
    public static void main(String[] args) {

        StringBuilder test = new StringBuilder();
        test.append("a");
        test.append("b");

        System.out.println(test.toString().matches(".*A.*"));


    }
}
