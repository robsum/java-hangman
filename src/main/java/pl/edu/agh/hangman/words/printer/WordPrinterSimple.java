package pl.edu.agh.hangman.words.printer;



public class WordPrinterSimple implements WordPrinter {
    private static final char HIDDEN_LETTER = '_';
    private final String word;

    public WordPrinterSimple(String word) {
        this.word = word.toUpperCase();
    }

    @Override
    public void print(String givenLettersToCheck) {
        char[] lettersToPrint = new char[word.length()];
        for (int i = 0; i < lettersToPrint.length; i++) {
            lettersToPrint[i] = HIDDEN_LETTER;
        }

        char[] wordLetters = word.toCharArray();

        for(int i = 0; i < wordLetters.length; i++) {
            char letterInWord = wordLetters[i];
            if (givenLettersToCheck.toUpperCase().contains(String.valueOf(letterInWord))) {
                lettersToPrint[i] = letterInWord;
            }
        }

        for (char letter : lettersToPrint) {
            System.out.print(" " + letter + " ");
        }
        System.out.println();
    }
}
