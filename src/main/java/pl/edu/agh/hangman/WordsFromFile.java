package pl.edu.agh.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class WordsFromFile implements Words {
    // losuje i zwraca
    // strategia do zwracania słow

    public final String filepath = "src/main/resources/slowa.txt";

    private ArrayList<String> words;

    public WordsFromFile() throws IOException {
        // read words file:
        words = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
                words.add(line);
            }
            String everything = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getRandomWord() {
        int index = getRandomNumber(0, words.size());
        return words.get(index);
    }

    @Override
    public String getWord() {
        return getRandomWord().toUpperCase(Locale.ROOT);
    }
}
