package pl.edu.agh.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordsFromFile implements WordsProvider{
    public final String filepath = "src/main/resources/slowa.txt";

    @Override
    public List<String> getWords() throws IOException {
        // read words file:
        List<String> words = new ArrayList<>();
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

        return words;
    }
}
