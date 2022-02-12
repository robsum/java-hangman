package pl.edu.agh.hangman.words.provider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordsFromFile implements WordsProvider {
    private final String filepath;

    public WordsFromFile(String filePath) {
        this.filepath = filePath;
    }

    @Override
    public List<String> getWords() {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (hasNoProperWord(line)) {
                    continue;
                }
                words.add(line.trim().toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private boolean hasNoProperWord(String line) {
        return line.isEmpty() || line.matches("[0-9_\\-\\+]+");
    }
}